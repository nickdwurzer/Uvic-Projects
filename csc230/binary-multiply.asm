# UVic CSC 230, Summer 2021
# Assignment #1, part C

# Student name: Nick Wurzer
# Student number: V00958568

# Using the binary multiply (i.e., repeated shfits + add) technique,
# multiply value in $8 with value in $9. The initial values in $8
# and $9 will always be less than 0x7FFF (i.e., only right-most 15
# bits are used, such that result will always fit into 32 bits and
# not otherwise cause an arithmetic overflow).
# Store the result of the multiply in $15.


.text

start:
	lw $8, testcase3_a  # STUDENTS MAY MODIFY THE TESTCASE GIVEN IN THIS LINE: Multiplicand
	lw $9, testcase3_b  # STUDENTS MAY MODIFY THE TESTCASE GIVEN IN THIS LINE: Multiplier
	
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
#Idea:  Loop through the 16 bits of the multiplier from right to left.  If a bit it in the multiplier is a 1, then we will shift the multiplicand
#by the count of the loop(the number of bits from the right the current bit is), then add it to the result thus far.
	li $10, 0 #current digit in multiplier
	li $11, 0 #left shift amount
	li $12, 0 #temp multiplicand
	li $13, 0 #temp multiplier
	li $15, 0 #result
loop:
	srlv $13, $9, $10
	andi $13, $13, 1
	addi $10, $10, 1
	beq $13, 1, shiftadd
	beq $10, 16, exit
	b loop
shiftadd:
	addi $11, $10, -1
	sllv $12, $8, $11
	add $15, $15, $12
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

# testcase1: Result is 0x15
testcase1_a:
	.word	0x00000003
testcase1_b:
	.word   0x00000007
	    

# testcase2: Result is 0x00006c98
testcase2_a:
	.word	0x000000c8   # decimal 200
testcase2_b:
	.word   0x0000008b   # decimal 139


# testcase3: Result is 0x3fff0001
testcase3_a:
	.word	0x00007fff   # decimal 32767
testcase3_b:
	.word   0x00007fff
	
