#!/usr/bin/env python
import argparse
import sys
def pair_sum(nums):
    dict_of_nums = dict()
    for i in range(len(nums)):
        dict_of_nums[int(nums[i])] = 1
    for i in range(len(nums)):
        if(225-int(nums[i]) in dict_of_nums):
            return True
    return False
def main():
    parser = argparse.ArgumentParser(description = "file parser")
    parser.add_argument("filename")
    if((len(sys.argv)) == 1):
        parser.print_help()
    for i in range(1,len(sys.argv)):
        my_file = parser.parse_args()
        with open(my_file.filename) as a_file:
            numbers = a_file.read()
            numbers = numbers.strip("\n")
            my_nums = list()
            my_nums = numbers.split(" ")
            print(pair_sum(my_nums))

main()