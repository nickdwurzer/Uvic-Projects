# Skeleton file provided to students in UVic CSC 230, Summer 2021
# Original file copyright Mike Zastre, 2021

.include "a4support.asm"

.data

.eqv	MAX_ARRAY_SIZE 1024

.align 2
ARRAY_1:	.space MAX_ARRAY_SIZE
ARRAY_2:	.space MAX_ARRAY_SIZE
ARRAY_3:	.space MAX_ARRAY_SIZE
ARRAY_4:	.space MAX_ARRAY_SIZE
ARRAY_5:	.space MAX_ARRAY_SIZE
ARRAY_6:	.space MAX_ARRAY_SIZE
ARRAY_7:	.space MAX_ARRAY_SIZE
ARRAY_8:	.space MAX_ARRAY_SIZE

# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

FILENAME_1:	.asciiz "integers-10-6.bin"
FILENAME_2:	.asciiz "integers-10-21.bin"

# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE



.globl main
.text 
main:

# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv


	# WRITE YOUR SOLUTION TO THE PART E PROBLEM
	# HERE...
	#read integers from FILENAME_1 into ARRAY_1
	la $a0, FILENAME_1
	la $a1, ARRAY_1
	jal read_file_of_ints
	add $s1, $zero, $v0
	#read integers from FILENAME_2 into ARRAY_2
	la $a0, FILENAME_2
	la $a1, ARRAY_2
	jal read_file_of_ints
	add $s2, $zero, $v0
	#A3 = accumulate_max(A1)
	la $a0, ARRAY_1
	la $a1, ARRAY_3
	add $a2, $s1, $zero
	jal accumulate_max
	#A4 = accumulate_max(A2)
	la $a0, ARRAY_2
	la $a1, ARRAY_4
	add $a2, $s2, $zero
	jal accumulate_max
	#A5 = reverse(A3)
	la $a0, ARRAY_3
	la $a1, ARRAY_5
	add $a2, $s1, $zero
	jal reverse_array
	#A6 = pairwise_max(A4, A5)
	la $a0, ARRAY_4
	la $a1, ARRAY_5
	la $a2, ARRAY_6
	add $a3, $s2, $zero
	jal pairwise_max
	#A7 = accumulate_sum(A6)
	la $a0, ARRAY_6
	la $a1, ARRAY_7
	add $a2, $s2, $zero
	jal accumulate_sum
	#output to console: A7[-1]
	la $a0, ARRAY_7
	#get last address in ARRAY_7
	mul $s2, $s2, 4
	add $a0, $a0, $s2 
	addi $a0, $a0, -4
	#output last integer in array to console
	lw $a0, 0($a0)
	addi $v0, $zero, 1
	syscall
	
	
	# Get outta here.		
	add $v0, $zero, 10
	syscall	
	

	
# COPY YOUR PROCEDURES FROM PARTS A, B, C, and D BELOW
# THIS POINT.

	
	
# Accumulate sum: Accepts two integer arrays where the value to be
# stored at each each index in the *second* array is the sum of all
# integers from the index back to towards zero in the first
# array. The arrays are of the same size; the size is the third
# parameter.
#
accumulate_sum:
	#ensure registers are empty
	add $t1, $zero, $zero
	add $t2, $zero, $zero
add_more_ints:
	lw $t0, 0($a0)#load word from array_a
	add $t1, $t1, $t0#calculate next sum
	sw $t1, 0($a1)#store word into array_b
	#Increment indeices and count for the loop
	addi $a0, $a0, 4
	addi $a1, $a1, 4
	addi $t2, $t2, 1
	#check if you've reached the end of the array
	beq $a2, $t2, end_accumulate_sum
	b add_more_ints
end_accumulate_sum:
	jr $ra


# Accumulate max: Accepts two integer arrays where the value to be
# stored at each each index in the *second* array is the maximum
# of all integers from the index back to towards zero in the first
# array. The arrays are of the same size;  the size is the third
# parameter.
#
accumulate_max:
	lw $t0, 0($a0) #$t1 holds teh next number in the array, to compare to the current maximum
	lw $t4, 0($a0) #$t4 is the current maximum
	add $t1, $t0, $zero#add first number into $t1
	add $t2, $zero, $zero#$t2 is the loop count
add_next_max:
	lw $t0, 0($a0)#load next word from array_a
	slt $t3, $t4, $t0#if current max < the next integer then set current max = next integer
	bnez $t3, set_new_max
after_set_new_max:
	add $t1, $t0, $zero#load next number into $t1
	sw $t4, 0($a1)#store word into array_b
	#increment loop and indices
	addi $a0, $a0, 4
	addi $a1, $a1, 4
	addi $t2, $t2, 1
	#check if the end of the array is reached
	beq $a2, $t2, end_accumulate_max
	b add_next_max
end_accumulate_max:
	sw $t4, 0($a1)
	jr $ra
set_new_max:
	add $t4, $zero, $t0#store max in t4
	b after_set_new_max
	
	
# Reverse: Accepts an integer array, and produces a new
# one in which the elements are copied in reverse order into
# a second array.  The arrays are of the same size; 
# the size is the third parameter.
#
reverse_array:
	#get the last index in the array
	add $t0, $a1, $zero
	mul $a2, $a2, 4
	add $a1, $a1, $a2
	addi $a1, $a1, -4
prepend_next_element:
	#store next lowest indexed element of input array into the next highest address of output array
	lw $t1, 0($a0)
	sw $t1, 0($a1)
	#Increment address of input array, decrement address of output array
	addi $a0, $a0, 4
	addi $a1, $a1, -4
	#check if you've reached the last index
	beq $a1, $t0, end_reverse_array
	b prepend_next_element
end_reverse_array:
	#store the last element
	lw $t1, 0($a0)
	sw $t1, 0($a1)
	jr $ra
	
	
# Reverse: Accepts three integer arrays, with the maximum
# element at each index of the first two arrays is stored
# at that same index in the third array. The arrays are 
# of the same size; the size is the fourth parameter.
#	
pairwise_max:
	#ensure $t3 has value 0
	add $t3, $zero, $zero
add_next_element:
	#load first elements of both input arrays into $t1 and $t2
	lw $t0, 0($a0)
	lw $t1, 0($a1)
	addi $t3, $t3, 1
	slt $t2 $t0, $t1 #if element in first array is less than element in second array, add element from second array to output array
	bgt $t3, $a3, end_pairwise_max #check if you've reached the end of the arrays
	bnez $t2, add_array_b_to_array_c
	b add_array_a_to_array_c
end_pairwise_max:
	jr $ra
	
add_array_b_to_array_c:
	#store appropriate element
	sw $t1, 0($a2)
	#increment indices
	addi $a0, $a0, 4
	addi $a1, $a1, 4
	addi $a2, $a2, 4
	b add_next_element
add_array_a_to_array_c:
	#store appropriate element
	sw $t0, 0($a2)
	#increment indices
	addi $a0, $a0, 4
	addi $a1, $a1, 4
	addi $a2, $a2, 4
	b add_next_element



# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE
