#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>

#define RESERVED 0x00000001

//Code adapted from example.c
struct __attribute__((__packed__)) superblock_t {
    uint8_t   fs_id [8];
    uint16_t block_size;
    uint32_t file_system_block_count;
    uint32_t fat_start_block;
    uint32_t fat_block_count;
    uint32_t root_dir_start_block;
    uint32_t root_dir_block_count;
};

int main(int argc, char* argv[]) {

    int fd = open(argv[1], O_RDWR);
    struct stat buffer;
    fstat(fd, &buffer);

    void* address=mmap(NULL, buffer.st_size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    struct superblock_t* sb;
    sb=(struct superblock_t*)address;
    printf("Super block information:\nBlock size: %d\n", htons(sb->block_size));
    printf("Block count: %d\n", htonl(sb->file_system_block_count));
    printf("FAT starts: %d\n", htonl(sb->fat_start_block));
    printf("FAT blocks: %d\n", htonl(sb->fat_block_count));
    printf("Root directory starts: %d\n", htonl(sb->root_dir_start_block));
    printf("Root directory blocks: %d\n", htonl(sb->root_dir_block_count));


    int fat_start = (htonl(sb->fat_start_block) * htons(sb->block_size))/4;
    int fat_end = ((htonl(sb->fat_start_block) * htons(sb->block_size)) + (htonl(sb->fat_block_count) * htons(sb->block_size)))/4;
    int num_free = 0;
    int num_reserved = 0;
    int num_allocated = 0;

    for(int i = fat_start; i<fat_end; i++){
        //check if free
        if(!(htonl(((uint32_t*)address)[i])))
        {
            //printf("free\n");
            num_free++;
        }
        //check if reserved
        else if(!(RESERVED - htonl(((uint32_t*)address)[i])))
        {
            //printf("reserved\n");
            num_reserved++;
        }
        else{
            num_allocated++;
        }
    }
    printf("\nFAT information:\nFree blocks: %d\nReserved blocks: %d\nAllocated blocks: %d\n", num_free, num_reserved, num_allocated);
    
    munmap(address,buffer.st_size);
    close(fd);
}