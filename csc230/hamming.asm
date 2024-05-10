# UVic CSC 230, Summer 2021
# Assignment #1, part B

# Student name: Nick Wurzer
# Student number: V00958568


.text

start:
	lw $8, testcase2_a  # STUDENTS MAY MODIFY THE TESTCASE GIVEN IN THIS LINE
	lw $9, testcase2_b  # STUDENTS MAY MODIFY THE TESTCASE GIVEN IN THIS LINE
	
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
#Idea: Exclusive-or the two registers.  Any place where the bits are different will have value 1.  
#Add together all digits whose value is one to get the hamming edit distance.
	#$10 used to store the number of differences between bits.
	#$11 used to make sure the number of bits checked is no more than 32.
	#$12 used as a mask when checking for differences in the rightmost bit.
	#$13 used to store the rightmost bit so that it can be added to the total distance.
	#$15 used to store the total hamming distance.
	xor $10, $8, $9
	li $11, 0
	li $12, 1
loop:
	and $13, $12, $10
	add $15, $15, $13
	srl $10, $10, 1
	addi $11, $11, 1
	beq $11, 32, exit
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

# testcase1: Hamming distance is 32
testcase1_a:
	.word	0x00000000
testcase1_b:
	.word   0xffffffff
	    

# testcase2: Hamming distance is 0
testcase2_a:
	.word	0xabcd0123
testcase2_b:
	.word   0xabcd0123
	
	
# testcase3: Hamming distance is 16
testcase3_a:
	.word	0xffff0000
testcase3_b:
	.word   0xaaaaaaaa
	
	
# testcase4: Hamming distance is 11
testcase4_a:
	.word	0xcafef00d
testcase4_b:
	.word   0xfacefade
