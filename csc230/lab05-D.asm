.data

stringA: .asciiz "We're off to see the wizard,"
stringB: .asciiz "the wonderful wizard of OZ!"
stringC: .asciiz "I'll be back..."
stringD: .asciiz "Doh!"

string_space:
	.asciiz "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"


# Given the string address loaded in $8,
# find the length of the string and store
# that length is $10

.text
	la $8, stringA
	la $9, string_space
	#add $11, $0, $0
	
loop_start:
	lb $11, 0($8)
	sb $11, 0($9)
	beq $11, $0, finish
	addi $8, $8, 1
	addi $9, $9, 1
	beq $0, $0, loop_start
	
finish:
	# Nothing more is needed
	addi $2, $0, 10
	syscall
	# At this point length of the string
	# is stored in $9
