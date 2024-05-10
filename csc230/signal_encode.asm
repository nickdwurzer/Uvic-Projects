# Skeleton file provided to students in UVic CSC 230, Summer 2021
# Original file copyright Mike Zastre, 2021

.text


main:	


# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

    ## Test code that calls procedure for part A
    #jal sos_send

    ## signal_flash test for part B
    # addi $a0, $zero, 0x24   # dot dot dash dot
    # jal signal_flash

    ## signal_flash test for part B
     #addi $a0, $zero, 0x73   # dash dash dash
     #jal signal_flash
        
    ## signal_flash test for part B
    #addi $a0, $zero, 0x23     # dot dash dot
    #jal signal_flash
            
    ## signal_flash test for part B
    #addi $a0, $zero, 0x11   # dash
    #jal signal_flash  
    
    # signal_message test for part C
    #la $a0, test_buffer
    #jal signal_message

    # one_alpha_encode test for part D
    # the letter 'p' is properly encoded as 0x64.
    # addi $a0, $zero, 'p'
    # jal one_alpha_encode  
    
    # one_alpha_encode test for part D
    # the letter 'a' is properly encoded as 0x12
    #addi $a0, $zero, 'a'
    #jal one_alpha_encode
    
    # one_alpha_encode test for part D
    # the space' is properly encoded as 0xff
    # addi $a0, $zero, ' '
    # jal one_alpha_encode
    
    # message_into_code test for part E
    # The outcome of the procedure is here
    # immediately used by signal_message
    la $a0, message02
    la $a1, buffer01
    jal message_into_code
    #la $a0, buffer01
    #jal signal_message
    

get_outta_here:
    # Proper exit from the program.
    addi $v0, $zero, 10
    syscall


	
	
###########
# PROCEDURE
sos_send:
	#grow stack and save return register for use later
	addi $sp, $zero, -4
	sw $ra, 0($sp)
	# S
	jal seven_segment_on
	jal delay_short
	jal seven_segment_off
	jal delay_short
	jal seven_segment_on
	jal delay_short
	jal seven_segment_off
	jal delay_short
	jal seven_segment_on
	jal delay_short
	jal seven_segment_off
	jal delay_short
	# O
	jal seven_segment_on
	jal delay_long
	jal seven_segment_off
	jal delay_short
	jal seven_segment_on
	jal delay_long
	jal seven_segment_off
	jal delay_short
	jal seven_segment_on
	jal delay_long
	jal seven_segment_off
	jal delay_short
	# S
	jal seven_segment_on
	jal delay_short
	jal seven_segment_off
	jal delay_short
	jal seven_segment_on
	jal delay_short
	jal seven_segment_off
	jal delay_short
	jal seven_segment_on
	jal delay_short
	jal seven_segment_off
	jal delay_short
	#restore return register and shrink stack
	lw $ra, 0($sp)
	addi $sp, $zero, 4
	jr $ra


# PROCEDURE
signal_flash:
	#save $ra and grow stack
	addi $sp, $sp, -16
	sw $ra, 0($sp)
	#if byte value is 0xff, pause for special character
	beq $a0, 0xff, pause
	#break up the high and low nibbles with appropriate mask
	andi $t0, $a0, 0xf0 #t0 is the high nibble
	srl $t0, $t0, 4
	andi $t1, $a0, 0x0f #t1 is the low nibble
	
	#loop through until the appropriate number of dots and dashes have been displayed
	li $t2, 4 #maximum of four dots and dashes
loop1:
	beqz $t1, end1
	beqz $t2, end1
	addi $t1, $t1, -1
	addi $t2, $t1, -1
	#mask the least-significant bit in the high nibble, to check if it is a dash or a dot
	andi $t4, $t0, 0x1
	beqz $t4, dot
	bnez $t4, dash
dot:	
	sw $t0, 4($sp)
	sw $t1, 8($sp)
	sw $t1, 12($sp)
	jal seven_segment_on
	jal delay_short
	jal seven_segment_off
	jal delay_short
	lw $t0, 4($sp)
	lw $t1, 8($sp)
	lw $t1, 12($sp)
	srl $t0, $t0, 1
	b loop1
dash:
	sw $t0, 4($sp)
	sw $t1, 8($sp)
	sw $t1, 12($sp)
	jal seven_segment_on
	jal delay_long
	jal seven_segment_off
	jal delay_short
	lw $t0, 4($sp)
	lw $t1, 8($sp)
	lw $t1, 12($sp)
	srl $t0, $t0, 1
	b loop1
pause:
	jal delay_long
	jal delay_long
	jal delay_long
end1:
	#restore return register and shrink stack
	lw  $ra, 0($sp)
	addi $sp, $sp, 16
	jr $ra

###########
# PROCEDURE
signal_message:
	addi $sp, $sp, -8
	sw $ra, 0($sp)
loop2:
	#store the byte in $t0
	andi $t0, 0x0
	lbu $t0, 0($a0)
	#if terminating character is reached then exit the loop
	beq $t0, $zero, end2
	#increment the address
	addi $a0, $a0, 1
	#store $a0 for later use
	sw $a0, 4($sp)
	#load byte into $a0 and call signal_flash
	add $a0, $zero, $t0
	jal signal_flash
	#restore $a0
	lw $a0, 4($sp)
	#continue loop2 if the byte is not 0
	b loop2
end2:
	#shrink stack and restore $ra
	lw $ra, 0($sp)
	addi $sp, $zero, 8
	jr $ra
	
	
###########
# PROCEDURE
#         ***note***  Since the test cases were given as characters, I've used the letter as the parameter
# instead of a data-memory address for the letter in the message.
one_alpha_encode:
	#$a0 contains byte equivalent of a letter
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	#check if the letter in $a0 is a space
	beq $a0, 0x20, space_character
	#load address of codes and zero out temp addresses
	la $t0, codes
	add $t3, $zero, $zero
	add $t4, $zero, $zero
	#check if the letter in $a0 is the same as the letter given to encode
loop3:
	lb $t1, 0($t0)
	beq $t1, $a0, found_letter
	addi $t0, $t0, 8
	b loop3
	#Once the letter is found, convert dots and dashes into the proper byte value
found_letter:
	addi $t0, $t0, 1
	lb $t1, 0($t0)
	beq $t1, $zero, end3
	beq $t1, '-', add_dash
	beq $t1, '.', add_dot
	
	#$t3 to be the number of dots/dashes-low nibble
	#$t4 to be the dot dash sequence-high nibble
add_dash:
	sll $t4, $t4, 1
	addi $t3, $t3, 1
	addi $t4, $t4, 1
	
	b found_letter
add_dot:
	sll $t4, $t4, 1
	addi $t3, $t3, 1
	b found_letter
space_character:
	li $t4, 0xf
	li $t3, 0xf
end3:
	#return the encoded byte value in $vo
	sll $t4, $t4, 4
	add $v0, $t3, $t4
	#restore $ra and shrink the stack
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra	


###########
# PROCEDURE
message_into_code:
	#grow stack and store $ra for future use
	addi $sp, $sp, -12
	sw $ra, 0($sp)
loop4:
	#check if the terminating character has been reached, if so exit the loop
	lbu $t1, 0($a0)
	beq $t1, $zero, end4
	#save registers
	sw $a0, 4($sp)
	sw $a1, 8($sp)
	lbu $a0, 0($a0)
	#use character value at address of $a0 in alpha_one_encode
	jal one_alpha_encode
	#restore registers
	lw $a0, 4($sp)
	lw $a1, 8($sp)
	#store the result of alpha encode in the buffer location
	sb $v0, 0($a1)
	#increment both the buffer address and the message address
	addi $a0, $a0, 1
	addi $a1, $a1, 1
	#continue the loop
	b loop4
end4:
	#shrink stack and restore $ra
	lw $ra, 0($sp)
	addi $sp, $sp, 12
	jr $ra


# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE


#############################################
# DO NOT MODIFY ANY OF THE CODE / LINES BELOW

###########
# PROCEDURE
seven_segment_on:
	la $t1, 0xffff0010     # location of bits for right digit
	addi $t2, $zero, 0xff  # All bits in byte are set, turning on all segments
	sb $t2, 0($t1)         # "Make it so!"
	jr $31


###########
# PROCEDURE
seven_segment_off:
	la $t1, 0xffff0010	# location of bits for right digit
	sb $zero, 0($t1)	# All bits in byte are unset, turning off all segments
	jr $31			# "Make it so!"
	

###########
# PROCEDURE
delay_long:
	add $sp, $sp, -4	# Reserve 
	sw $a0, 0($sp)
	addi $a0, $zero, 600
	addi $v0, $zero, 32
	syscall
	lw $a0, 0($sp)
	add $sp, $sp, 4
	jr $31

	
###########
# PROCEDURE			
delay_short:
	add $sp, $sp, -4
	sw $a0, 0($sp)
	addi $a0, $zero, 200
	addi $v0, $zero, 32
	syscall
	lw $a0, 0($sp)
	add $sp, $sp, 4
	jr $31

#############
# DATA MEMORY
.data
codes:
    .byte 'a', '.', '-', 0, 0, 0, 0, 0
    .byte 'b', '-', '.', '.', '.', 0, 0, 0
    .byte 'c', '-', '.', '-', '.', 0, 0, 0
    .byte 'd', '-', '.', '.', 0, 0, 0, 0
    .byte 'e', '.', 0, 0, 0, 0, 0, 0
    .byte 'f', '.', '.', '-', '.', 0, 0, 0
    .byte 'g', '-', '-', '.', 0, 0, 0, 0
    .byte 'h', '.', '.', '.', '.', 0, 0, 0
    .byte 'i', '.', '.', 0, 0, 0, 0, 0
    .byte 'j', '.', '-', '-', '-', 0, 0, 0
    .byte 'k', '-', '.', '-', 0, 0, 0, 0
    .byte 'l', '.', '-', '.', '.', 0, 0, 0
    .byte 'm', '-', '-', 0, 0, 0, 0, 0
    .byte 'n', '-', '.', 0, 0, 0, 0, 0
    .byte 'o', '-', '-', '-', 0, 0, 0, 0
    .byte 'p', '.', '-', '-', '.', 0, 0, 0
    .byte 'q', '-', '-', '.', '-', 0, 0, 0
    .byte 'r', '.', '-', '.', 0, 0, 0, 0
    .byte 's', '.', '.', '.', 0, 0, 0, 0
    .byte 't', '-', 0, 0, 0, 0, 0, 0
    .byte 'u', '.', '.', '-', 0, 0, 0, 0
    .byte 'v', '.', '.', '.', '-', 0, 0, 0
    .byte 'w', '.', '-', '-', 0, 0, 0, 0
    .byte 'x', '-', '.', '.', '-', 0, 0, 0
    .byte 'y', '-', '.', '-', '-', 0, 0, 0
    .byte 'z', '-', '-', '.', '.', 0, 0, 0
    
message01:  .asciiz "we used to look up at the sky and wonder at our place in the stars, now we just look down and worry about our place in the dirt"
message02:  .asciiz "sos"
message03:  .asciiz "thriller"
message04:  .asciiz "billie jean"
message05:  .asciiz "the girl is mine"
message06:  .asciiz "pretty young thing"
message07:  .asciiz "human nature"
message08:  .asciiz "we are the world"
message09:  .asciiz "off the wall"
message10:  .asciiz "i want you back"

buffer01:   .space 128
buffer02:   .space 128
test_buffer:    .byte 0x03 0x73 0x03 0x00    # This is SOS
