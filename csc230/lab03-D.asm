.text 
	# $8 : initial value for which we look for trailing zeros
	# $9 : the counter to keeps track of # of trailing zeros (result)
	# $10 :  the result of the AND with the mask
	
	ori $8, $0, 0   	# put 0 in register 8: this value will result in an infinite loop
	
	ori $9, $0, 0		# counter
loop:
	andi $10, $8, 1
	bne $10, $0, exit
	addi $9, $9, 1
	srl $8, $8, 1
	b loop
	
exit:
	nop			# answer is in $9
	
	
#A value of 0 in register 8 will result in an infinite loop.  To fix this bug we could check if the value in
#register 9 is equal to 32, if so we would exit the program.  This is because each register holds 32 bits, so if the counter
#reaches 32 we would know that the original number in register 8 was 0 and had a 0 in every bit of the register.