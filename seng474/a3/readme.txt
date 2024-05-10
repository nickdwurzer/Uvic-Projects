Nick Wurzer
V00958568

How to run code from assignment 3.

There are two python files with code for this assignment, agglo_clustering.py and k_means.py.
They run code for the agglomerative clustering and k-means sections of the assignment respectively.

agglo_cluster.py
To reproduce figures in the report associated with agglomerative clustering, simply run the main function in agglo_cluster.py.
As you close each plot, the next section of code will run and the next plot will be shown until all figures have been produced.

k_means.py
There are two sections in the main fucntion of this file.
One generates a plot for the total cost as a function of the number of clusters, and one displaying the clustering for a set number of clusters.
You should only run one section at a time because sometimes you want the clustering plots to be shown and sometimes you don't.

If you are running the first section of the main function, you are runnign many iterations of clustering, so you don't want the clustering plots to show.
Leave the plots commented out in Lloyds_2d() and Lloyds_3d() functions.

If you are running the second section, then you want to see the clustering plots.
To see the initializations, uncomment the parts at lines 100 and 158.
To see the plots after each iteration of k-means, uncomment the parts at lines 127 and 188.
To see the final clustering, uncomment the parts at lines 141 and 206.

Once you have the section you would like to run in the main function uncommented, and the corresponding plots uncommented in Lloyds_2d() and Lloyds_3d simply run the main function.