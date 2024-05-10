
	.data
ARRAY_A:
	.word	21, 210, 49, 4
ARRAY_B:
	.word	21, -314159, 0x1000, 0x7fffffff, 3, 1, 4, 1, 5, 9, 2
ARRAY_Z:
	.space	28
NEWLINE:
	.asciiz "\n"
SPACE:
	.asciiz " "
		
	
	.text  
main:	
	la $a0, ARRAY_A
	addi $a1, $zero, 4
	jal dump_array
	
	la $a0, ARRAY_B
	addi $a1, $zero, 11
	jal dump_array
	
	la $a0, ARRAY_Z
	lw $t0, 0($a0)
	addi $t0, $t0, 1
	sw $t0, 0($a0)
	addi $a1, $zero, 9
	jal dump_array
		
	addi $v0, $zero, 10
	syscall

# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
	
dump_array:
	addi $sp, $zero, -4
	sw $ra, 0($sp)
	add $a2, $a0, $zero
	#print the numbers in the array separated by spaces. If the total has been reached, print a new line and return to main code.
dump_array_loop:
	lw $a0, 0($a2)
	addi $v0, $zero, 1
	syscall
	la $a3, SPACE
	lb $a0, 0($a3)
	addi $v0, $zero, 11
	syscall
	addi $a2, $a2, 4
	addi $a1, $a1, -1
	beqz $a1, end_dump_array
	b dump_array_loop
end_dump_array:
	la $a3, NEWLINE
	lb $a0, 0($a3)
	addi $v0, $zero, 11
	syscall
	lw $ra, 0($sp)
	addi $sp, $zero, 4
	jr $ra
	
	
	
# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE
