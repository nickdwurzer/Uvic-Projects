.data

nums:
	.word 1, 3, 5, -11, 22, 33, -4, 5, 0

	
.text
	li $12, 0
	la $11, nums
	ori $13, $11, 0
	addi $13, $13, 32
loop:
	lw $14, ($11)
	add $12, $12, $14
	addi $11, $11, 4
	beq $11, $13  exit
	b loop
exit:
li $2, 10
syscall