#!/usr/bin/env python3
import matplotlib.pyplot as plt
import pandas as pd
from scipy.cluster.hierarchy import dendrogram, linkage
from sklearn.cluster import AgglomerativeClustering

class cluster:
    def __init__(self, data) -> None:
        self.data = pd.read_csv(data).to_numpy()
    
    def scatter_3d(self):
        size = [2]*len(self.data)
        fig = plt.figure(figsize=(10, 7))
        ax = fig.add_subplot(projection = "3d")
        ax.scatter(self.data[:, 0], self.data[:, 1], self.data[:, 2], s = size)
        ax.view_init(0, 80)
        plt.show()


    def create_clusters_3d(self, linkage_type, n_clusters):
        size = [2]*len(self.data)
        agglo_cluster = AgglomerativeClustering(n_clusters= n_clusters, linkage=linkage_type)
        agglo_cluster.fit_predict(self.data)
        fig = plt.figure(figsize=(10, 7))
        ax = fig.add_subplot(projection = "3d")
        ax.scatter(self.data[:,0], self.data[:,1], self.data[:,2], c=agglo_cluster.labels_, s = size, cmap='rainbow')
        ax.view_init(0, 80)
        plt.show()



    def scatter_2d(self):
        size = [2]*len(self.data)
        plt.scatter(self.data[:,0], self.data[:,1], s = size)
        plt.show()


    def create_dendrogram(self, linkage_type, p):
        linked = linkage(self.data, linkage_type)
        plt.figure(figsize=(10, 7))
        dendrogram(linked,
                distance_sort='descending',
                show_leaf_counts=True,
                truncate_mode= "lastp",
                p = p)
        plt.show()


    def create_clusters_2d(self, linkage_type, n_clusters):
        size = [2]*len(self.data)
        agglo_cluster = AgglomerativeClustering(n_clusters= n_clusters, linkage=linkage_type)
        agglo_cluster.fit_predict(self.data)
        _ = plt.scatter(self.data[:,0], self.data[:,1], c=agglo_cluster.labels_, s = size, cmap='rainbow')
        plt.show()

    def main():
        cluster1 = cluster("dataset1.csv")
        cluster2 = cluster("dataset2.csv")
        
        cluster1.scatter_2d()
        cluster2.scatter_3d()

        cluster1.create_dendrogram("single", 200)
        cluster1.create_dendrogram("average", 200)
        cluster2.create_dendrogram("single", 200)
        cluster2.create_dendrogram("average", 200)

        cluster1.create_clusters_2d("single", 9)
        cluster1.create_clusters_2d("average", 2)

        cluster2.create_clusters_3d("single", 5)
        cluster2.create_clusters_3d("average", 2)

        

        

if __name__ == "__main__":
    cluster.main()