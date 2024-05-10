# UVic CSC 230, Summer 2021
# Assignment #1, part A

# Student name: Nick Wurzer
# Student number: V00958568

# Determine the number of left-most zeros in register $8's value
# Store this number in register $15


.text

start:
	lw $8, testcase5  # STUDENTS MAY MODIFY THE TESTCASE GIVEN IN THIS LINE
	
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
# Idea: Use mask to determine if the left most bit is a zero.  If not, branch to exit.  Otherwise add 1 to register 15 and 
# shift bits tot he left by one.  repeat until a 1 is found, or until all 32 bits have been checked.
	#initialize
	#register 15 used for the result number of zeros
	#register 8 used to keep track of number of zeros left after shift
	#register 3 to be zero unless the left-most bit in $8 has value 1 ($3 is the mask)
	li $15, 0
loop:
	andi $3, $8, 0x80000000
	bnez $3, exit
	beq $15, 32, exit
	addi $15, $15, 1
	sll $8, $8, 1
	b loop
# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE


exit:
	add $2, $0, 10
	syscall
		

.data

# Note: These test cases are not exhaustive. The teaching team
# will use other test cases when evaluating student submissions
# for this part of the assignment.

testcase1:
	.word	0x00200020    # left-most zero bits is 10 

testcase2:
	.word 	0xfacefade    # left-most zero bits is 0
	
testcase3:
	.word  0x01020304     # left-most zero bits is 7
	
testcase4:
	.word  0x0000000c    # left-most zero bits is 28
	
testcase5:
	.word  0x7000000b     # left-most zero bits is 1

