#!/usr/bin/env python3
import pandas as pd
import numpy as np
import random
import matplotlib.pyplot as plt

class k_means:
    def __init__(self, data, k) -> None:
        self.k = k
        self.data = pd.read_csv(data).to_numpy()
        self.means_index = None

    def initialize_random_means(self):
        self.means_index = []
        for i in range(0, self.k):
            next_index = random.randint(0, self.data.shape[0])
            if(next_index not in self.means_index):
                self.means_index += [random.randint(0, self.data.shape[0])]
            else:
                i-=1

    def initialize_plus_plus_means_2d(self):
        #each point has probability of being next center proportional to its cost (squared distance)
        #first point is random
        self.means_index = [random.randint(0, self.data.shape[0])]
        indexes = list(range(0, len(self.data)))
        
        #for each cluster center
        for k in range(1,self.k):
            smallest_costs = [float("inf")]*len(indexes)
            #get list of weights
            #for each possible point left to choose
            for i in range(0,len(indexes)):
                smallest_cost = float("inf")
                means = [[self.data[i, 0], self.data[i, 1]] for i in self.means_index]
                #check against each mean
                for j in range(0, len(means)):
                    #calculate the distance from the data point to the mean
                    cost = pow(k_means.euclidian_distance_2d(self.data[i][0], self.data[i][1], means[j][0], means[j][1]), 2)
                    #if the distance is less than the current shortest distance
                    if(cost < smallest_cost):
                        #update the shortest distance
                        smallest_cost = cost
                #assign the point to the cluster with the closest mean
                smallest_costs[i] = smallest_cost
            #use random.choices() to pick an index, uses replacement so be sure to choose uniquely
            next_mean_index = random.choices(population = indexes, weights = smallest_costs, k = 1)
            self.means_index += next_mean_index
            indexes = indexes[:next_mean_index[0]] + indexes[next_mean_index[0]+1:]

    def initialize_plus_plus_means_3d(self):
        #each point has probability of being next center proportional to its cost (squared distance)
        #first point is random
        self.means_index = [random.randint(0, self.data.shape[0])]
        indexes = list(range(0, len(self.data)))
        
        #for each cluster center
        for k in range(1,self.k):
            smallest_costs = [float("inf")]*len(indexes)
            #get list of weights
            #for each possible point left to choose
            for i in range(0,len(indexes)):
                smallest_cost = float("inf")
                means = [[self.data[i, 0], self.data[i, 1], self.data[i, 2]] for i in self.means_index]
                #check against each mean
                for j in range(0, len(means)):
                    #calculate the distance from the data point to the mean
                    cost = pow(k_means.euclidian_distance_3d(self.data[i][0], self.data[i][1], self.data[i][2], means[j][0], means[j][1], means[j][2]), 2)
                    #if the distance is less than the current shortest distance
                    if(cost < smallest_cost):
                        #update the shortest distance
                        smallest_cost = cost
                #assign the point to the cluster with the closest mean
                smallest_costs[i] = smallest_cost
            #use random.choices() to pick an index, uses replacement so be sure to choose uniquely
            next_mean_index = random.choices(population = indexes, weights = smallest_costs, k = 1)
            self.means_index += next_mean_index
            indexes = indexes[:next_mean_index[0]] + indexes[next_mean_index[0]+1:]

    def average(l1):
        return (sum(l1)/len(l1))
    
    def euclidian_distance_2d(x1, y1, x2, y2):
        return np.sqrt(pow((x2-x1), 2)+pow((y2-y1), 2))
    
    def euclidian_distance_3d(x1, y1, z1, x2, y2, z2):
        return np.sqrt(pow((x2-x1), 2)+pow((y2-y1), 2)+pow((z2-z1), 2))
    
    def loyds_2d(self):
        means = [[self.data[i, 0], self.data[i, 1]] for i in self.means_index]
        smallest_costs = [float("inf")]*len(self.data)
        colours = [0]*len(self.data)
        size = [2]*len(self.data)
        j = 1
        for i in self.means_index:
            colours[i] = j
            size[i] = 30
            j+=1
        #UNCOMMENT THIS TO SEE THE INITIAL CLUSTER CENTERS
        """_ = plt.scatter(self.data[:,0], self.data[:,1], c = colours, s = size, cmap='rainbow')
        #plt.show()"""

        size = [2]*len(self.data)
        #assign each point to the cluster with the closest mean
        prev_colours = colours.copy()
        stop = False
        #while at least one datapoint has been updated
        while(not stop):
            #for all data points
            for i in range(0,len(self.data)):
                smallest_cost = float("inf")
                smallest_cost_index = -1
                #check against each mean
                for j in range(0, len(means)):
                    #calculate the distance from the data point to the mean
                    cost = pow(k_means.euclidian_distance_2d(self.data[i][0], self.data[i][1], means[j][0], means[j][1]), 2)
                    #if the distance is less than the current shortest distance
                    if(cost < smallest_cost):
                        #update the shortest distance
                        smallest_cost = cost
                        #update the cluster the point will be assigned to
                        smallest_cost_index = j
                #assign the point to the cluster with the closest mean
                colours[i] = smallest_cost_index
                smallest_costs[i] = smallest_cost
            #UNCOMMENT THIS TO SEE THE PLOT DURING EACH ITERATION OF CLUSTERING
            """_ = plt.scatter(self.data[:,0], self.data[:,1], c = colours, s = size, cmap='rainbow')
            plt.show()"""

            for j in range(0, len(means)):
                if(len([self.data[i][0] for i in range(0, len(colours)) if colours[i] ==  j])>0):
                    means[j][0] = k_means.average([self.data[i][0] for i in range(0, len(colours)) if colours[i] ==  j])
                    means[j][1] = k_means.average([self.data[i][1] for i in range(0, len(colours)) if colours[i] ==  j])
            
            if(prev_colours == colours):
                stop = True

            prev_colours = colours.copy()

        #UNCOMMENT THIS SECTION TO SEE THE FINAL CLUSTERING PLOT
        """_ = plt.scatter(self.data[:,0], self.data[:,1], c = colours, s = size, cmap='rainbow')
        plt.show()"""

        return sum(smallest_costs)
    

    def loyds_3d(self):
        means = [[self.data[i, 0], self.data[i, 1], self.data[i, 2]] for i in self.means_index]
        smallest_costs = [float("inf")]*len(self.data)
        colours = [0]*len(self.data)
        size = [2]*len(self.data)
        j = 1
        for i in self.means_index:
            colours[i] = j
            size[i] = 40
            j+=1
        #UNCOMMENT THIS TO SEE THE INITIAL CLUSTER CENTERS
        """fig = plt.figure(figsize=(10, 7))
        ax = fig.add_subplot(projection = "3d")
        ax.scatter(self.data[:,0], self.data[:,1], self.data[:,2], s = size, c=colours, cmap='rainbow')
        ax.view_init(0, 80)
        plt.show()"""

        size = [2]*len(self.data)
        #assign each point to the cluster with the closest mean
        prev_colours = colours.copy()
        stop = False
        #while at least one datapoint has been updated
        while(not stop):
            #for all data points
            for i in range(0,len(self.data)):
                smallest_cost = float("inf")
                smallest_cost_index = -1
                #check against each mean
                for j in range(0, len(means)):
                    #calculate the distance from the data point to the mean
                    cost = pow(k_means.euclidian_distance_3d(self.data[i][0], self.data[i][1], self.data[i][2], means[j][0], means[j][1], means[j][2]), 2)
                    #if the distance is less than the current shortest distance
                    if(cost < smallest_cost):
                        #update the shortest distance
                        smallest_cost = cost
                        #update the cluster the point will be assigned to
                        smallest_cost_index = j
                #assign the point to the cluster with the closest mean
                colours[i] = smallest_cost_index
                smallest_costs[i] = smallest_cost
            #UNCOMMENT THIS TO SEE THE PLOT DURING EACH ITERATION OF CLUSTERING
            """fig = plt.figure(figsize=(10, 7))
            ax = fig.add_subplot(projection = "3d")
            ax.scatter(self.data[:,0], self.data[:,1], self.data[:,2], s = size, c=colours, cmap='rainbow')
            ax.view_init(0, 80)
            plt.show()"""

            for j in range(0, len(means)):
                if(len([self.data[i][0] for i in range(0, len(colours)) if colours[i] ==  j])>0):
                    means[j][0] = k_means.average([self.data[i][0] for i in range(0, len(colours)) if colours[i] ==  j])
                    means[j][1] = k_means.average([self.data[i][1] for i in range(0, len(colours)) if colours[i] ==  j])
                    means[j][2] = k_means.average([self.data[i][2] for i in range(0, len(colours)) if colours[i] ==  j])
            
            if(prev_colours == colours):
                stop = True

            prev_colours = colours.copy()

        #UNCOMMENT THIS SECTION TO SEE THE FINAL CLUSTERING PLOT
        """fig = plt.figure(figsize=(10, 7))
        ax = fig.add_subplot(projection = "3d")
        ax.scatter(self.data[:,0], self.data[:,1], self.data[:,2], s = size, c=colours, cmap='rainbow')
        ax.view_init(0, 80)
        plt.show()"""

        return sum(smallest_costs)

    def main():
#TOTAL COST VS NUMBER OF CLUSTERS
#############################################################################################
        """means = list(range(1, 25))
        costs = [0]*len(means)
        for i in means:
            k_means1 = k_means("dataset1.csv", i)
            k_means1.initialize_random_means()
            costs[i-1] = k_means1.loyds_2d()
            print(i)
        plt.title("How K (the Number of Clusters) Affects Total Cost (Sum of Squared Distances from Cluster Centers)")
        plt.xlabel("K")
        plt.ylabel("Total Cost")
        plt.plot(means, costs)
        plt.show()"""
#############################################################################################
        """means = list(range(1, 25))
        costs = [0]*len(means)
        for i in means:
            k_means2 = k_means("dataset2.csv", i)
            k_means2.initialize_random_means()
            costs[i-1] = k_means2.loyds_3d()
            print(i)
        plt.title("How K (the Number of Clusters) Affects Total Cost (Sum of Squared Distances from Cluster Centers)")
        plt.xlabel("K")
        plt.ylabel("Total Cost")
        plt.plot(means, costs)
        plt.show()"""
#############################################################################################
        """means = list(range(1, 25))
        costs = [0]*len(means)
        for i in means:
            k_means1 = k_means("dataset1.csv", i)
            k_means1.initialize_plus_plus_means_2d()
            costs[i-1] = k_means1.loyds_2d()
            print(i)
        plt.title("How K (the Number of Clusters) Affects Total Cost (Sum of Squared Distances from Cluster Centers)")
        plt.xlabel("K")
        plt.ylabel("Total Cost")
        plt.plot(means, costs)
        plt.show()"""
#############################################################################################
        """means = list(range(1, 25))
        costs = [0]*len(means)
        for i in means:
            k_means2 = k_means("dataset2.csv", i)
            k_means2.initialize_plus_plus_means_3d()
            costs[i-1] = k_means2.loyds_3d()
            print(i)
        plt.title("How K (the Number of Clusters) Affects Total Cost (Sum of Squared Distances from Cluster Centers)")
        plt.xlabel("K")
        plt.ylabel("Total Cost")
        plt.plot(means, costs)
        plt.show()"""

#CLUSTERING WITH OPTIMAL NUMBER OF CLUSTERS
#############################################################################################
        """k_means1 = k_means("dataset1.csv", 4)
        k_means1.initialize_random_means()
        k_means1.loyds_2d()"""
#############################################################################################
        """k_means1 = k_means("dataset1.csv", 4)
        k_means1.initialize_plus_plus_means_2d()
        k_means1.loyds_2d()"""
#############################################################################################
        """k_means2 = k_means("dataset2.csv", 6)
        k_means2.initialize_random_means()
        k_means2.loyds_3d()"""
#############################################################################################
        """k_means2 = k_means("dataset2.csv", 6)
        k_means2.initialize_plus_plus_means_3d()
        k_means2.loyds_3d()"""


if __name__ == "__main__":
    k_means.main()