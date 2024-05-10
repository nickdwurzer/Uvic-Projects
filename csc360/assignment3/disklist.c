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
    int root_dir_start = (htonl(sb->root_dir_start_block) * htons(sb->block_size))/4;
    int root_dir_end = ((htonl(sb->root_dir_start_block) * htons(sb->block_size)) + (htonl(sb->root_dir_block_count) * htons(sb->block_size)))/4;

    for(int i = root_dir_start; i<root_dir_end; i+=16)
    {
        uint32_t status = htonl(((uint32_t*)address)[i]);
        if(status & 0x1000000)
        {

            if(status & 0x2000000)
            {
                printf("F\n");
            }
            else if(status & 0x4000000)
            {
                printf("D\n");
            }
        }
    }
    
    munmap(address,buffer.st_size);
    close(fd);
}