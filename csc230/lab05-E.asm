.data

stringA: .asciiz "We're off to see the wizard,"
stringB: .asciiz "the wonderful wizard of OZ!"
stringC: .asciiz "I'll be back..."
stringD: .asciiz "Doh!"

string_space:
	.space 100



.text
	la $a0, stringD
	la $a1 string_space
	jal loop_start
	beq $0, $0, finish

loop_start:
	lb $t0, 0($a0)
	sb $t0 ($a1)
	beq $t0, $zero, return_from_string_len
	addi $a0, $a0, 1
	addi $a1, $a1, 1
	beq $zero, $zero, loop_start
return_from_string_len:
	jr $ra
	
finish:
	li $v0, 10
	syscall

