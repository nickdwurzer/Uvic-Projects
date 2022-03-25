#!/usr/bin/env python3
from packet_struct.packet_struct import packet
import sys

def main():
    packet_count = 0
    packets = []
    print()
    endian = "big"
    with open(sys.argv[1], "rb") as f:
        global_header = f.read(24)
        if(global_header[:4] == b'\xd4\xc3\xb2\xa1'):
            endian = "little"
        else:
            endian = "big"
        this_zone = int.from_bytes(global_header[8:12], endian)

        packet_header = f.read(16)
        while(packet_header):
            packet_count+=1
            #total packet length
            incl_len = packet_header[8:12]
            packet_data = f.read(int.from_bytes(incl_len, endian))
            #create packet object
            new_packet = packet()
            new_packet.packet_No_set(packet_count)
            new_packet.IP_header.get_IP(packet_data[26:30], packet_data[30:34])
            new_packet.TCP_header.get_src_port(packet_data[34:36])
            new_packet.TCP_header.get_dst_port(packet_data[36:38])
            new_packet.timestamp_set(packet_header[:4], packet_header[4:8], this_zone)
            new_packet.TCP_header.get_flags(packet_data[47:48])
            packets.append((packet_header, packet_data, new_packet))
            packet_header = f.read(16)
        total_connections = calc_total_connections(packets)
        print("A) Total connections ", total_connections, end = "\n--------------------------------------------------\n")
        address_and_port_info = get_address_and_port(packets)
        status = get_status(packets, address_and_port_info)
        print("B) Connections' details", end = "\n\n")
        for i in range(len(address_and_port_info)):
            print("Connection ", i + 1, ":", sep = "")
            print("Source Address:", address_and_port_info[i][0])
            print("Destination Address:", address_and_port_info[i][1])
            print("Source Port:", address_and_port_info[i][2])
            print("Destination Port:", address_and_port_info[i][3])
            print("Status: S",status[i][0], "F", status[i][1],sep = "", end = "")
            if(status[i][2] == 0):
                print()
            else:
                print("/R")
            if(status[i][0] != 0 and status[i][1] !=0):
                times = get_times(packets, address_and_port_info, this_zone)
                print("Start time:", times[i][0])
                print("End time:", times[i][1])
                print("Duration:", times[i][1]-times[i][0])
            print("END\n++++++++++++++++++++++")
        print("--------------------------------------------------")
        print("C) General\n")
        num_complete = len(get_complete_connections(status))
        print("Total number of complete TCP connections:", num_complete)
        num_reset = len(get_reset_connections(status))
        print("Number of reset TCP connections:", num_reset)
        print("Number of TCP connections that were still open when trace capture ended:", total_connections - num_complete)
        print("--------------------------------------------------")
        print("D) Complete TCP Connections\n")
        min_time = sys.maxsize
        max_time = 0
        total_time = 0
        times = get_times(packets, address_and_port_info, this_zone)
        for i in times:
            if((i[0] != "") and (i[1] != "")):
                if((i[1]-i[0]) < min_time):
                    min_time = i[1]-i[0]
            if((i[0] != "") and (i[1] != "")):
                if((i[1]-i[0]) > max_time):
                    max_time = i[1]-i[0]
                total_time += i[1]-i[0]
        print("Minimum time duration:", min_time)
        print("Mean time duration:", total_time/num_complete)
        print("Maximum time duration:", max_time)





def get_times(packets, address_and_port_info, this_zone):
    first_packet = packets[0][2].timestamp
    times = []
    for i in range(len(address_and_port_info)):
        times.append(["",""])
    for i in range(len(packets)):
        if(packets[i][2].TCP_header.flags["SYN"] == 1):
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.dst_ip, packets[i][2].IP_header.src_ip, packets[i][2].TCP_header.dst_port, packets[i][2].TCP_header.src_port))
            except:
                pass
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.src_ip, packets[i][2].IP_header.dst_ip, packets[i][2].TCP_header.src_port, packets[i][2].TCP_header.dst_port))
            except:
                pass
            if(times[index][0] == ""):
                times[index][0] = packets[i][2].timestamp - first_packet

    for i in range(len(packets)):
        if(packets[i][2].TCP_header.flags["FIN"] == 1):
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.dst_ip, packets[i][2].IP_header.src_ip, packets[i][2].TCP_header.dst_port, packets[i][2].TCP_header.src_port))
            except:
                pass
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.src_ip, packets[i][2].IP_header.dst_ip, packets[i][2].TCP_header.src_port, packets[i][2].TCP_header.dst_port))
            except:
                pass
            times[index][1] = packets[i][2].timestamp - first_packet
            
    return times

def get_reset_connections(status):
    reset_conns = []
    for i in range(len(status)):
        if(status[i][2] != 0):
            reset_conns.append(i)
    return reset_conns

def get_complete_connections(status):
    complete_conns = []
    for i in range(len(status)):
        if(status[i][0] != 0 and status[i][1] !=0):
            complete_conns.append(i)
    return complete_conns

def get_status(packets, address_and_port_info):
    status = []
    index = 0
    for i in range(len(address_and_port_info)):
        status.append([0,0,0])
    for i in range(len(packets)):
        if(packets[i][2].TCP_header.flags["FIN"] == 1):
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.dst_ip, packets[i][2].IP_header.src_ip, packets[i][2].TCP_header.dst_port, packets[i][2].TCP_header.src_port))
            except:
                pass
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.src_ip, packets[i][2].IP_header.dst_ip, packets[i][2].TCP_header.src_port, packets[i][2].TCP_header.dst_port))
            except:
                pass
            #print(index)
            status[index] = [status[index][0], status[index][1] + 1, status[index][2]]

    for i in range(len(packets)):
        if(packets[i][2].TCP_header.flags["RST"] == 1):
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.dst_ip, packets[i][2].IP_header.src_ip, packets[i][2].TCP_header.dst_port, packets[i][2].TCP_header.src_port))
            except:
                pass
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.src_ip, packets[i][2].IP_header.dst_ip, packets[i][2].TCP_header.src_port, packets[i][2].TCP_header.dst_port))
            except:
                pass
            status[index] = [status[index][0], status[index][1], status[index][2] + 1]

    for i in range(len(packets)):
        if(packets[i][2].TCP_header.flags["SYN"] == 1):
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.dst_ip, packets[i][2].IP_header.src_ip, packets[i][2].TCP_header.dst_port, packets[i][2].TCP_header.src_port))
            except:
                pass
            try:
                index = address_and_port_info.index((packets[i][2].IP_header.src_ip, packets[i][2].IP_header.dst_ip, packets[i][2].TCP_header.src_port, packets[i][2].TCP_header.dst_port))
            except:
                pass
            status[index] = [status[index][0] +1, status[index][1], status[index][2]]

    return status


def calc_total_connections(packets):
    k = set()
    for j in packets:
        k = k | {(j[2].IP_header.src_ip, j[2].IP_header.dst_ip, j[2].TCP_header.src_port, j[2].TCP_header.dst_port)}
    return int(len(k)/2)

def get_address_and_port(packets):
    m = []
    for j in packets:
        if(((j[2].IP_header.dst_ip, j[2].IP_header.src_ip, j[2].TCP_header.dst_port, j[2].TCP_header.src_port) not in m) and (j[2].IP_header.src_ip, j[2].IP_header.dst_ip, j[2].TCP_header.src_port, j[2].TCP_header.dst_port) not in m):
            m.append((j[2].IP_header.src_ip, j[2].IP_header.dst_ip, j[2].TCP_header.src_port, j[2].TCP_header.dst_port))

    return m


if __name__ == "__main__":
    main()
