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
        #print("global header: ", global_header)
        if(global_header[:4] == b'\xd4\xc3\xb2\xa1'):
            endian = "little"
        else:
            endian = "big"
        this_zone = int.from_bytes(global_header[8:12], endian)
        #print(this_zone)

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
        print("A) Total connections: ", total_connections, end = "\n-------------------------\n")
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
            print("END\n++++++++++++++++++++++")
        #for j in packets:
            #print(j[2].IP_header.src_ip, j[2].IP_header.dst_ip, j[2].TCP_header.src_port, j[2].TCP_header.dst_port, sep = ",  ")
        #print("packet count: ", len(packets))
        #print(packets[0][2].timestamp - packets[-1][2].timestamp)
        #ethernet_headers = get_ethernet_headers(packets)
        #ipv4_headers = get_ipv4_headers(packets)

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
