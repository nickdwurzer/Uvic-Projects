# This code assumes the use of the "Bitmap Display" tool.
#
# Tool settings must be:
#   Unit Width in Pixels: 32
#   Unit Height in Pixels: 32
#   Display Width in Pixels: 512
#   Display Height in Pixels: 512
#   Based Address for display: 0x10010000 (static data)
#
# In effect, this produces a bitmap display of 16x16 pixels.


	.include "bitmap-routines.asm"

	.data
TELL_TALE:
	.word 0x12345678 0x9abcdef0	# Helps us visually detect where our part starts in .data section
KEYBOARD_EVENT_PENDING:
	.word	0x0
KEYBOARD_EVENT:
	.word   0x0
DIAMOND_ROW:
	.word	9
DIAMOND_COLUMN:
	.word	9
	
DIAMOND_COLOUR_1:
	.word 0x00db93c0
	
	.eqv LETTER_a 97
	.eqv LETTER_d 100
	.eqv LETTER_w 119
	.eqv LETTER_s 115
	.eqv LETTER_space 32
	
	.globl main
	
	.text	
main:
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	# initialize variables
	
check_for_event:

    beq $zero, $zero, check_for_event
    
    
    # Should never, *ever* arrive at this point
    # in the code.  

    addi $v0, $zero, 10

.data
    .eqv DIAMOND_COLOUR_BLACK 0x00000000
.text

    addi $v0, $zero, DIAMOND_COLOUR_BLACK
    syscall

	

draw_bitmap_diamond:
 
# You can copy-and-paste some of your code from part (c)
# to provide the procedure body.
#
    jr $ra



	.kdata

	.ktext 0x80000180
#
# You can copy-and-paste some of your code from part (b)
# to provide elements of the interrupt handler.
#

__kernel_entry:


__exit_exception:
	eret

.data

# Any additional .text area "variables" that you need can
# be added in this spot. The assembler will ensure that whatever
# directives appear here will be placed in memory following the
# data items at the top of this file.

    
# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE


.eqv DIAMOND_COLOUR_WHITE 0x00FFFFFF
