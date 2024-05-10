	.globl main

	.data
KEYBOARD_EVENT_PENDING:
	.word	0x0
KEYBOARD_EVENT:
	.word   0x0
KEYBOARD_COUNTS:
	.space  128
NEWLINE:
	.asciiz "\n"
SPACE:
	.asciiz " "
DONE:
	.asciiz "done"
	
	.eqv  LETTER_a 97
	.eqv  LETTER_space 32
	
	
	.text  
main:
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	la $s0, 0xffff0000	# control register for MMIO Simulator "Receiver"
	lb $s1, 0($s0)
	ori $s1, $s1, 0x02	# Set bit 1 to enable "Receiver" interrupts (i.e., keyboard)
	sb $s1, 0($s0)
	
check_for_event:
    la $s0, KEYBOARD_EVENT_PENDING
    lw $s1, 0($s0)
    bnez  $s1, is_new_event
    b check_for_event
    #if there was an event, incriment keyboard_counts, reset keyboard_event_pending and check if the key was a space
    #go to "done_processing_keypress after
is_new_event:
	la $t0, KEYBOARD_COUNTS
	lw $t1, 0($t0)
	addi $t1, $t1, 1
	sw $t1, 0($t0)
	la $t0, KEYBOARD_EVENT_PENDING
	sw $zero, 0($t0)
	la $t0, KEYBOARD_EVENT
	lw $t1, 0($t0)
	la $t2, SPACE
	lb $t3, 0($t2)
	beq $t1, $t3, space_detected
	b done_processing_keypress
	#if the new event is a space, print the number of lower-case letters typed so far
space_detected:
	la $t0, KEYBOARD_COUNTS
	lw $t1, 0($t0)
	addi $t1, $t1, -1
	sw $t1, 0($t0)
	addi $v0, $zero, 1
	la $t0, KEYBOARD_COUNTS
	lw $a0, 0($t0)
	syscall
	la $a3, NEWLINE
	lb $a0, 0($a3)
	addi $v0, $zero, 11
	syscall
	b done_processing_keypress
	
	#print done after a keypress has been processed
done_processing_keypress:
	la $a3, DONE
	la $a0, 0($a3)
	addi $v0, $zero, 4
	syscall
	la $a3, NEWLINE
	lb $a0, 0($a3)
	addi $v0, $zero, 11
	syscall
	#back to the loop
	b check_for_event
	
	
	
	
	#code below is adapted from lab 8
	.kdata

	.ktext 0x80000180
__kernel_entry:
	mfc0 $k0, $13		# $13 is the "cause" register in Coproc0
	andi $k1, $k0, 0x7c	# bits 2 to 6 are the ExcCode field (0 for interrupts)
	srl  $k1, $k1, 2	# shift ExcCode bits for easier comparison
	beq $zero, $k1, __is_interrupt
	
__is_exception:
	# Something of a placeholder...
	# ... just in case we can't escape the need for handling some exceptions.
	beq $zero, $zero, __exit_exception
	
__is_interrupt:
	andi $k1, $k0, 0x0100	# examine bit 8
	bne $k1, $zero, __is_keyboard_interrupt	 # if bit 8 set, then we have a keyboard interrupt.
	beq $zero, $zero, __exit_exception	# otherwise, we return exit kernel
	
__is_keyboard_interrupt:
	addi $k0, $zero, 1
	la $k1, KEYBOARD_EVENT_PENDING
	sw $k0, 0($k1)
	
	la $k0, 0xffff0004
	lw $k1, 0($k0)
	la $k0, KEYBOARD_EVENT	
	sw $k1, 0($k0)	
	# Note: We could also take the value obtained from the "lw:
	# and store it someplace in data memory. However, to keep
	# things simple, we're using $t7 immediately above.
	
	beq $zero, $zero, __exit_exception	# Kept here in case we add more handlers.
__exit_exception:
	eret

# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE	
