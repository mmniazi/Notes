# Cluster
- A group of nodes which work on a single task
- Automatic scaling and healing
- Master node coordinates task to worker nodes

# Deployment
- Using kubectl to create deployment
- Once you have a running Kubernetes cluster, you can deploy your containerized applications on top of it. To do so, you create a Kubernetes Deployment. The Deployment is responsible for creating and updating instances of your application. Once you've created a Deployment, the Kubernetes master schedules the application instances that the Deployment creates onto individual Nodes in the cluster.
- The Deployment controller replaces an instance if the Node hosting it goes down or it is deleted. This provides a self-healing mechanism to address machine failure or maintenance.

# Pods
Pod to host your application instance. A Pod is a Kubernetes abstraction that represents a group of one or more application containers (such as Docker or rkt), and some shared resources for those containers. Those resources include:

- Shared storage, as Volumes
- Networking, as a unique cluster IP address
- Information about how to run each container, such as the container image version or specific ports to use

# Node
A Pod always runs on a Node. A Node is a worker machine in Kubernetes and may be either a virtual or a physical machine, depending on the cluster. Each Node is managed by the Master. A Node can have multiple pods, and the Kubernetes master automatically handles scheduling the pods across the Nodes in the cluster. The Master's automatic scheduling takes into account the available resources on each Node.

Every Kubernetes Node runs at least:
- Kubelet, a process responsible for communication between the Kubernetes Master and the Nodes; it manages the Pods and the containers running on a machine.
- A container runtime (like Docker, rkt) responsible for pulling the container image from a registry, unpacking the container, and running the application.

# Service
A Kubernetes Service is an abstraction layer which defines a logical set of Pods and enables external traffic exposure, load balancing and service discovery for those Pods.