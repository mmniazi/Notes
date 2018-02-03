======
WEEK 1
======


Cloud Computing Concepts:
- Internals of cc
- distributed systems & cc

Key Phrases/Concepts
 Keep your eyes open for the following key terms or phrases as you complete the readings and interact with the lectures. These topics will help you better understand the content in this module.

 - Clouds
 - MapReduce paradigm
 - Hadoop YARN

Guiding Questions
 Develop your answers to the following guiding questions while completing the readings and working on assignments throughout the week.

 - Why is cloud computing popular today?
 - What is different in cloud computing compared to previous generations of distributed systems?
 - How does one program in MapReduce?
 - How does the MapReduce system schedule jobs?


Cloud computing involves
 - storage
 - compute
 - networks

------------
Lecture 1.1:
------------

 Amazon AWS offers following services:
  - EC2 - Elastic Compute Cloud   // provides computing services
  - S3  - Simple Storage Service  // store data
  - EBS - Elastic Block Storage   // volume storage that the EC2 instances can access
  
 Categories of Cloud  
  - Private clouds - // for eg private company cloud, accessible by employees only
  - Public clouds - // pay and use
  
  
------------
Lecture 1.4:
------------

// When someone tells you about some cloud computing problem, check if the problem is a combination of any of the following factors:
Four features new in today's cloud (same thing for a distributed system, later - lecture 2.1):
 1. Massive scale     // 100,000+ servers, run on as many servers
  - In 2010 MS was adding 10K servers every month for Bing!
  - facebook increased its servers from 30K to 180 K from 2009-2012
  - Google exact nos aren't known but they are widely reputed that they are running one of the biggest nos!
 2. On demand access     // no contract, no upfront fees
  - HaaS - Hardware as a Service    - Not good, since you need to buy the hardwar and maintain it
  - IaaS - Infrastructure as a Service - good. Eg AWS, GCE etc
  - PaaS - Platform as a Service   - Access to flexible computing and storage infra, coupled with software platform (eg google's app engine - python, java, Go)
  - SaaS - Software as a Service       - access to sw service when you need them. eg - google docs, MS office on demand
 3. Data Intensive nature   // a lot of data
  - MPI based, high performance computing (HPC) - 'Compute Intensive',
  - but now the focus is shifting to 'Data Intensive' - store data at data centres & compute nodes near by.
  - CPU utilization is  no longer the most important resource metric, instead I/O (disk and/or network)
 4. New cloud programming paradigms  // ways to process this huge data
  - Google - map reduce (open source version - hadoop by yahoo), Sawzall (interpretting large data by performing parallel analysis)
  - Amazon offers elastic map reduce service (pay as you go)
 
Datacentres use a lot of water and power:
1. WUE = (Annual water usage) / (IT equipment energy)   // Water Usage Efficiency // low is good
2. PUE = (Total Facility Power) / (IT equipment power)  // Power Usage Efficiency // low is good, eg google=1.11


------------
Lecture 1.6:
------------
Economies of Cloud:
Break even analysis - buy or rent
- startups use cloud, probably they don't know how long will the company run
- Cloud providers benefit the most from 'storage'


------------
Lecture 2.1:
------------
cloud = distributed system, not vice versa

Cloud consists of:
- 100-1000s machines in a data centre (server side)
- 1000-1,000,000 machines accessing the services (client side)
- servers talk to each other   // aka cluster, which is a distributed system
- clients talk to the server   // which is a distributed system
- clients talk to each other    // peer to peer, eg bit torrent, distributed system


Examples of distributed systems:
 - client communicating with a server
 - bit torrent
 - the internet
 - the web (servers and clients)
 - hadoop
 - data centres

Working definition of distributed systems:
 .. is a collection of entities (processes), each of which is :
 - autonomous
 - programmable
 - asynchronous
 - failure-prone
 .. and which communicate through an :
 - unreliable communication medium
 
In solving these problems of distributed systems, many challenges abound:
 1. Failures    - no longer the exception, but rather a norm
 2. Scalability - 1000s of machines, Terra B of data
 3. Asynchrony  - clock skew and clock drift
 4. Concurrency - 1000s of machines interacting with each other accessing the same data
 
------------
Lecture 3.1:
------------
MapReduce paradigm:
(Map Reduce was invented by Google)
(Yahoo made an open source implementation that became 'Apache Hadoop')

The terms 'map' & 'reduce' are borrowed from functional languages (eg lisp):
 For eg, for sum of squares, the syntax is:
  (map square '(1 2 3 4))     // map
  - output: (1 4 9 16)
  [processes each record sequentially and independently]
  
  (reduce + '(1 4 9 16))     // reduce
  - (+16 (+9 (+4 1)))
  - Output: 30
  [processes set of all records in batches]
  
A single large task can be divided into several 'map tasks'.
Reduce phase takes the output of all the 'map tasks' and processes them.

Hash Partioning:
 Keys are assigned to reduce# = hash(key) % #of reduce servers // load balancing
 
 
Examples:
 - Counting the frequency of words in a text.
  - Divide input into several 'map tasks'
  - Output of each map tasks are assigned to reduce phase (with specific keys assigned to each reducer) 
 - Say given a web graph (A->B, meaning site A has a link to site B)
  - Map - process web logs and for each input <source, target>, it outputs <target, source>
  - Reduce - emits <taget, list(source)>
  
  
------------
Lecture 3.3:
------------
// Map-Reduce programming

Externally: For user
 - Write a map & reduce program (short)
 - submit job, wait for results
 - need to know nothing about parallel/distributed programming
 
Internally: For the Paradigm & the scheduler
 - Parallelize map
 - Transfer data from Map to Reduce
 - Parallelize reduce
 - Implement storage for map input, output & for reduce input, output.
 (Ensure that no Reduce starts before all Maps are finished. That is, ensure the barrier between the Map phase & Reduce phase)
 
 
Inside MapReduce
 1. Parallelize Map - each map task is independent of each other
  - All the map output records with same key assigned to same Reduce
 2. Transfer data from Map to Reduce
  - same key outputs assigned to same Reduce,
  - use partition function for this, eg hash(key) % no of reducers
 3. Parallelize Reduce - each reduce task is independent of each other
 4. Implement storage for map i/o, reduce i/o:
  - Map input - from distributed file system
  - Map output - to local disk (at Map nodes); uses local file system
  - Reduce input - from multiple remote disks; uses local file system // not Distributed, these are remote disks - for faster processing
  - Reduce output - to distributed file system
 // local file system - Linux FS etc,
 // distributed file system - GFS (Google File System), HDFS (Hadoop Distributed File System)
 // Resource Manager - assigns Maps and Reducers to servers
 
 
YARN Scheduler:
 (Yet Another Resource Scheduler)
  - Used in Hadoop 2.x+
  - Treats each server as a set of containers (some CPU & memory)
  - Has 3 components
   - Global Resource Manager (RM)      - Scheduling
   - Per-server Node Manager (NM)      - Daemon and server specific functions
   - Per-application (job) Application Master (AM) - Container negotiation with RM & NM, detecting task failures of that job
   

------------
Lecture 3.3:
------------
// MapReduce Fault Tolerance

- Server Failures
 - NM heartbeats to RM - If server fails, RM lets all affected AMs know, and AMs take action
 - NM keeps track of each task running at its server - If the task fails while in-progress, mark the task as idle and restart it.
 - AM heartbeats to RM - On failure, RM restarts AM, which then syncs up with its running tasks   
- RM failures
 - Use old checkpoints and bring up secondary RM 
 - Heartbeats also used to piggyback container requests to avoid extra messages
 
 
--------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
 

 
======
WEEK 2
====== 

Key Phrases/Concepts
 Keep your eyes open for the following key terms or phrases as you interact with the lectures. These topics will help you better understand the content in this module.

 - Failure detectors
 - Membership protocols
 - Gossip/epidemic protocols
 - Grid computing
 
 
Guiding Questions
 Develop your answers to the following guiding questions while completing the activities throughout the week.

 - Why are gossip and epidemic protocols fast and reliable?
 - What is the most efficient way for cloud computing systems to detect failures of servers?
 - How is grid computing related to cloud computing?




Overview:
 - Building blocks for distributed systems (in the cloud)
  - Gossip or epidemic protocols
  - Failure detection and Membership protocols
 - Programming assignment is an implementation of these building blocks 
 - Grid computing
  - Another pre-cursor to clouds  

----------
Lecture 1:
----------

  
Multicast problems: Fault tolerance & Scalability
// multicast protocol sits at the application level
// using TCP or UDP
 - Nodes may crash
 - packets may be dropped
 - 1000s of nodes  

Gossip aks Epidemic protocol:
 // solution to multicast problem, ie how to emit information to serveral nodes 
 
Muticast
 - Centralized
 - Tree-based
  - Build a spanning tree among the processes of the multicast group
  - Use spanning tree to disseminate multicasts
  - Use Acks/Nacks to repair multicasts not received
  - SRM (Scalable Reliable Multicast)
   - Used NAKs // when the msg is not recvd after sometime, to request again
   - But adds random delay, uses exponential backoff to avoid NAK storms
  - RMTP (Reliable Multicast Transport Protocol) 
   - Uses ACKs
   - But ACKs only sent to designated receivers, which then re-transmit  missing multicasts
  - These protocols still cause an O(N) ACK/NAK overhead 
 - Third approach
  - sender periodically transmits to 'b' random targets, the message is called a 'Gossip msg', could be sent over UDP
  - receiving nodes do the same after, some delay, ie send out copies of Gossip to 'b' random targets
  - When a node receives a Gossip, we say it is infected. 
  - The nodes may choose to send random Gossips, or most recent ones first or based on some priority of Gossips, it depends on the protocol used.
  
Push Vs Pull vs hybrid Gossip:
 - Push, there is a sender that pushes the Gossip to other nodes
  - lightweight in large groups
  - spreads a multicast quickly
  - Is highly fault tolerant
 - Pull, Periodically poll a few randomly selected processes for new multicast messages that you haven't received, get those msgs
 - Hybrid, push-pull, mixture of both
 - In all Gossip forms, it takes O(logN) rounds before about N/2 gets the gossip
 - pull gossip is faster than the push gossip
 - Second half of pull gossips finishes in time O(log(logN))
 
Topology aware gossip
 - Say 2 Racks connected by a router/switch. If the senders pick receivers at random, half of the traffic will go to the other rack,
  - hence load on router = O(N)
 - To avoid this problem, say the rack 1 subnet contains ni nodes,
  - pick gossip targets in your subnet with probability = (1-1/ni) 
  - load on router becomes = O(N)
  - pick gossip targets in the other subnet with probability = 1/ni
  
  
NNTP Inter-server protocol
 - Each client uploads/downloads news posts from each server
 - then the servers talk to each other and exchange the news posts, that way every server has all the posts
 -
 
 
----------
Lecture 2.1:  // Memberships
----------

NOTE: Processes in the text below means servers. In a data center with large # of servers, each server is called a process.
 // process failure = server failure

Failure are pretty common in data center's, here's why:
 - say MTTF (Mean Time To Failure) for 1 server is 10 years = 120 months
 - If the data centre has 120 servers, MTTF = 1 month
 - Typical data centre has 12,000 servers, MTTF = 7.2 hours !!
 
Solution: Build a failure detector program

Target setting here is:
 - Clouds/Datacenters
 - Replicated servers
 - Distributed databases

- Crash-stop/Fail-stop process failures

Group memebership services:
 - There is a membership list - all the working processes in the system (that have not failed)
 - Other applications access this list
 - There is a membership protocol through which it gets to know what processes have failed, what processes have joined the system
 - But the membership protocol makes use of an unreliable  communication medium which can drop/delay packets
 - This membership list could be:
  - Strongly Consisten // complete list all the time, eg virtual synchrony
  - Weakly Consistent // Almost complete list , eg Gossip style
  - Other systems // partial random list, eg SCAMP, T-MAN, Cyclon
 - The system has two other main components that:
  - Disseminate information
  - Failure detector // process joining, leaving
   - when a process fails, some process gets to know about the failure.
   - The process that has detected the failure, uses the dissemination component to let the rest of the processes know & update their membership status.
  
  
  
Books:
1.  (Ken Birman) Reliable Distributed Systems: Technologies, Web Services and Applications
 slides //  http://www.cs.cornell.edu/ken/book/New%20514%20slide%20set/index.htm  
 
 
----------
Lecture 2.2:  // Failure detectors (FD)
----------
- If a process fails, atleast one process will know about it
- It'll disseminate this info to the rest of the processes

 
Distributed Failure Detector properties:
 #1 Completeness  - each failure is detected
 #2 Accuracy   - there is no mistaken detection 
----------> it is impossible to build a FD with completeness as well as accuracy
   // impossible together in lossy network 
   // real FD  guarantees #1, for #2 offers Partial/Probabilistic guarantee
 #3 Speed   - time to detect the failure
 #4 scale   - equal load on each member
     - n/w msg load

Failure detector via Heartbeating:

 #1 Centralized Heartbeating (well known failure detector protocol)
  - Client's send HB periodically, each time incrementing a local counter
  - server keeps a timer to recieve HB within a specified time
  - Disadvantage - the central server gets overloaded with so much traffic


 #2 Ring Heartbeating:
  - All servers in a ring
  - each sends a HB to its left & right peer, each time incrementing the counter
  - Pros - Avoids hot-spotting // single server recv'ing too much traffic
  - Cons - Unpredictable on simultaneous multiple failures
      - repairing the ring is another overhead
    
 #3 All-to-all HB:
  - each process sending HB to every other process
  - pretty robust
  - Cons - if a process is slow and gets HB from other processes after a delay, it might mark the other processes as dead.
      - This will be a faulty detection, and 'completeness' here will not be 100%
    
 #4 Gossip style HB:
  - Each process gets HB from all other processes
  - Each process maintains a table with 3 columns -
   - process id // server id
   - # of HB // counter that it keeps incrementing after it gets a HB per process
   - Time last HB updated
  - Each process at random sends out it membership table at random as gossip
  - the recving process merges the two tables row-by-row
  - If it had a lesser count of HB for a process, it updates it to the higher count, and copies its current time to 3rd col.
  - Failure is detected when x sec (T-fail) elapse and no HB is recvd
  - But the member is deleted after T-clean (~=T-fail) sec
  - T-gossip (periodic time at which successive gossips are sent) has a trade off between 'bandwidth' & 'failure detection time'
   - higher T-Gossip = high bandwidth (good), high failure detection time (bad)
   - lower T-Gossip  =  low bandwidth (bad), low failure detection time (good)
  - False Positive Rate (Pmistake) relation to T-fail, T-cleanup when increased
   - detection time increases, but Pmistake - lower
  - TRADEOFF
   - false +ve rate VS
   - Detection time VS
   - Bandwidth 
   
   
----------
Lecture 2.4:  // Which is the best FD?
----------

#1. All-2-all HB
 - T=failure detection time
 - N= no of members
 - Load, L = N/T // load per process

#2. Gossip based approach
 // every Tg units = gossip period, send O(N) gossip messages
 - T=logN * Tg
 - L = N/Tg = N*LogN/T  // higher load in comparison to #1
         // because it provides higher accuracy by using more msgs
       
What's the best optimal that can be done?
 - Worst case load L* (per member) as a function of:
  - T  - period
  - PM(T) - probability of mistakes
  - N  - members
  - Pml = independent msg loss probability
  
 L* = log( PM(T) )/( T * log(Pml))

  - optimal L* is independent of N !
  - #1 & #2 are sub-optimal
   - L=O(N/T)
   - try to achieve simulataneous detection at all processes
   - fail to distinguish FD & dissemination components
 - So, the key here is to separate the two components - FD & dissemination
 - Use a non-HB based failure detection component
 
----------
Lecture 2.5:  // Another probabilistic FD
----------
// comes closer to the optimal FD 
// The SWIM effort is motivated by the unscalability of traditional heart-beating protocols, which either impose network loads that grow quadratically with group size, or compromise response times or false positive frequency w.r.t. detecting process crashes.
https://www.cs.cornell.edu/~asdas/research/dsn02-swim.pdf

Swim failure detector protocol
// Scalable Weakly-consistent Infection-style process group Membership Protocol = SWIM protocol
 - Instead of using HB, use ping'ing
 - Any process Pi
  - runs a Protocol period = T' units
// 1st chance  
  - picks one of the processes, Pj at random - sends a ping
   - If the other process sends back an ACK, Pi does nothing else in T'
// 2nd chance   
   - If the ping fails, it sends ping again via an indirect path to K other randomly selected processes
    - these k other processes send the ping to Pj
    - Pj replies with an ACK to these K processes
    - If Pi recevies atleast 1 ping reply from these k processes with in T', it's happy
    - If it doesn't, Pi marks Pj as failed
    
    
Swim vs HB
// for fixed 'false +ve rate' & 'message loss rate'
 - For HB, if the msg load is high, detection time is low, & vice versa
 - SWIM on the other hand provides constant detection time as well as constant process load

Swim:
 - First detection time = constant, independent of group size
         = E(e/(e-1)) // E read as expectation
 - load = constant per period
 - False +ve rate = Tunable via K (randome group of processes in chance 2 above) 
    - Falls exponentially as load is scaled
 - Completeness - Deterministic time-bounded
       - within O(log(N)) periods w.h.p.
       - Key: select each membership element once as a ping target in a traversal
         - Round robin pinging
         - random permutation of list after each traversal
         - each failure is detected in worst case 2N-1 (local) protocol periods
         - Preserves FD properties
 - Accuracy
  - Probablilty of mistakes (PM(T)) is exponential in -K & also depends on Pml (independent msg loss probability)
  
  
----------
Lecture 2.6:  // Dissemination and suspicion
----------

Dissemination options:
 - Multicast (HW/IP)
  - unreliable
  - mutiple simultaneous multicasts
  - not necessary enabled on all switches/routers
 - Point-to-point (TCP/UDP)
  - expensive // when talking to 1000s of processes in a group
 - Zero extra messages // piggyback on FD messages
  - infection style dissemination
  - specially when using SWIM style FD - where membership updates are disseminated on ACK/ping msgs
  - Failure detected by 1 process is disseminated through the group in O(logN) time
  
Suspicion mechanism
 - False detection due to:
  - Perturbed processes
  - packet loss, eg, from congestion
 - Indirect pinging may not solve the problem
  - eg, correlated message losses near pinged host
 - Key: suspect a process before declaring it as Failed in the group
 
Maintain a state machine
 - Pi maintain state machine for Pj in its membership list
 - by default the state = ALIVE
 - If FD detects Pj as failed, move to state SUSPECTED
 - In addition start disseminating via SWIM piggy back msgs - saying 'suspect' Pj failed
  - When is state SUSPECTED, Pi might recv direct/indirect ACK from Pj
  - Move back to state ALIVE
  - The switch between ALIVE & SUSPECTED can happen quite often, use counters/incarnation nos
 - After some timeout, move Pj state to FAILED
 
 
Wrap up:
 - Failure are the norm, not the exception in Data Centres
 - Every distributed system uses a failure detector
 - Many distributed systems use a membership service
 - Ring Failure detection underlies
  - IBM SP2 and many other similar clusters/machines
 - Gossip style failure detection underlies
  - Amazon EC2/S3 (rumoured)  
  
  
----------
Lecture 3.1:  // Grid applications
----------

Can HPC applications (like Rapid Atmospheric Modelling System - RAMS, wheather predication app) run w/o a supercomputer?

An application coded by a physicist / biologist / meteorologist
 - Say 4 jobs (0,1,2,3)
 - #0 output is consumed by #1 & #2 concurrently, & their o/p goes into #3
 - The output data could be several GBs
 - It may take serveral hours/days to process it
 - 4 stages of a job
  - Init
  - Stage in
  - Execute
  - Stage out
  - Publish
 - Computation intensive jobs, so Massively Parallel

Scheduling problem:
 - Given the nature of these jobs & grid resources, how would these be scheduled?  
 
 
----------
Lecture 3.2:  // Scheduling Problem
----------
 
2-level scheduling infra

// Level#2
 - Each site could be running an intra-site protocol for scheduling
  - For eg, given sites (university resources/data-centres) & their protocols in action
   - Wisconsin - HTCondor Protocol
   - NCSA   - Some other intra-site protocol
   - MIT  - Some other intra-site protocol
 - Responsible for:
  - Internal allocation & scheduling
  - Monitoring
  - Distribution and publishing of files
 
// Level#1   
 - On a global level, we might running an inter-site protocol, eg Globus protocol

Level#1 protocol distributes among sites, & level#2 protocol within the site decides how to schedule the given tasks

HTCondor Protocol (Level#2):
 - High-throuput computing system from U. Wisconsin Madison
 - Belongs to class of Cycle-scavenging systems // students using systems during day time & at night being used to process these tasks
  - Runs on a lot of workstations
  - when workstation free, ask site's central server (or Globus - Level#1 protocol) for tasks
  - If a user hits keystroke or mouse click, stop task
   - Either kill task or ask server to reschedule task
  - can also run on dedicated systems 
  
Globus protocol (level#1)
 - Internal structure of the sites is invisible to it
 - Responsible for:
  - External allocation & scheduling
  - Stage in & stage out of files (say from site 1 to site 2)
  
Globus toolkit:
 - Open source
 - Components
  - GridFTP - Wide area transfer of bulk data
  - GRAM5 - Grid Resource Allocation Manager
   - submit, locate, cancel & manage jobs
   - NOT a scheduler
   - Globus communicates with the schedulers in intra-site protocols like HTCondor or Portable Batch System (PBS)
  - RLS - Replica Location Service
   - naming service that translates from a file/dir name to a target location (or another file/dir name) 
  - Libraries like XIO to provide  a std API for all GRID IO functionalities
  - Grid Security Infra-structure (GSI)
  
// Security is imp for GRID computing, since sites are scattered
Security issues:
 - because GRIDs are 'federated' ie no single entity controls the entire infra
 - Single sign-on - collective job set should require once-only user authentication
 - Mapping to local security mechanism - some sites use Kerberos, others use Unix
 - Delegation - credentials to access resources inherited by subcomputations, eg job0 to job1
 - Communication authorization - eg 3rd party authentication
 - These are also imp in clouds, but less so because clouds are typically run under a central control
 - In clouds, the focus is on failures, scale, on-demand nature
 
 
Summary
 - Grid computing focusses on computation-intensive computing (HPC)
 - Though often federated, architecture & key concepts have a lot in common with that of clouds
 - Are Grid/HPC converging towards clouds?
  - Eg compare OpenStack & Globus architecture
  // lots of commonality but a little disjoint in terms of software & the standards that they develop as well as the conferences where the research gets published
  
  
  
--------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
 

 
======
WEEK 3
====== 
  
----------
Lecture 1:  P2P system introduction
----------
  
Why study p2p systems?
 - 1st distributed systems that seriously focussed on scalability with respect to the no of nodes
  // 100 million clients talking to each other
 - p2p techniques abound in cloud computing systems
  - Key-value stores (eg Cassandra, Riak, Voldemort - linkedIN, dynama - Amazon) use Chord p2p hashing
  
  
For eg, Limewire, an app from where one seaches (or used to) for music, movies etc. One would search for the song name and the app would return with several results (peers having the file being requested). User then would click on one of the results and the file would be downloaded (probably from serveral peers in parallel). 


What we'll study in this course:
 - Napster
 - Gnutella
 - Fasttrack (Kazaa, Kazaalite, Grokster)
 - Bit-torrent

P2P systems with provable properties:
 - Chord
 - Pastry
 - Kelips 
 
----------
Lecture 2:  Napster
----------
 
- Each user runs Napster client, these users are called peers. There stored info is scanned by the server.
- Napster.com runs some servers. These servers store directory info with file/peer pointer 
- Note that the servers don't store the files, they just store the directory info of peers.


Client:
 - It connects to the server and uploads list of music files that it wants to share
 - sever maintains a list of <filename, ip_addr, port> tuples. SERVER STORES NO FILES.
 - Search:
  - client sends server keywords to search with
  - servers searches its list with the keyword (ternary (3-children) tree algorithm)
  - server returns a list of hosts <ip:port> tuples to the client
  - client pings each of the hosts in the list to find the transfer rate
  - client fetches files from the best host
 - Communication:
  - All comm uses TCP 
  
  
Joining a P2P systems:
 - which server to join? servers keep changing all the time.
 - client sends an http request to well known url for that P2P service - http://www.myp2service.com
 - msg via DNS is routed to the 'instroducer', well known server that keeps track of some recently joined  nodes in the p2p system
 - introducer initializes  new peers' neighbor table
 
Problems:
 - Centralized server a source of congestion
 - Centralized server a single point of failure
 - no security - plain text msgs & pwds
 - napster.com declared to be responsible for users' copyright violation
  - "indirect infringement"
  - next system - Gnutella
  
  
  
----------
Lecture 3:  Gnutella
----------
  
 - Eliminate the servers
 - client machines search and retrieve amongst themselves
 - Clients acts as servers too, called SERVents
 - AOL released it in 2000 but immediately withdrawn due to copyright issues, but 88K users by 2003
 - original design underwent several revisions, but this course will focus on the 1st version that was published as tech-report 
 
Design
 - peers store
  - their own files
  - also store peer pointers (ip:port) // overlay graph, overlaid on top of internet
  
How do I seach for a file?
 - Gnutella routes different msgs within overlay graph
 - Gnutella protocol has 5 main msg types
  - Query  // search
  - Queryhit  // response to query 
  - Ping      // to probe n/w for other peers
  - Pong      // reply to ping, contain address of another peer
  - Push      // used to initiate file transfer
  
Going into the msg structure and protocol details:
 - All fields except for IP are in little-endian format
 - 0x12345678 stored as 0x78 in lowest address byte, then 0x56 in next higher address, and so on
 
Gnutella msg header format
 - 16 bits - Descriptor ID   // ID of this search transaction
 - 16 bits - Payload descriptor  // type of payload
 - 8 bits  - TTL     // decremented at each hop, msg dropped when it reaches 0. Init value ~7-10
 -     - Hop     // incremented at each hop
 -     - payload len      // depending upon the msg type, the length of the payload varies, this specifies that length
 
 
Messages in details:
 - Query (0x80)
  - contains min speed expected of peers
  - search criteria - what to search for
  - Query's flooded out, ttl restricted, forwarded once only
  - Recv'ing peer searches for the keyword in its own files, and floods out the query msg to all of its peers (except for the one that just fwded it the msg)
  - Peers that have already recvd/fwded the query have the desciptor Id field to find out if the arriving search request has already been processed by it // fwd only once
  - the chain proceeds untill ttl is 0 // ttl restricted
 - Query Hit (0x81)
  - when the peer that recved the msg, has to reply back to the requester, it replies with a 'Query Hit' msg
  - The header contains:
   - Number of hits for the requested search
   - its ip:port
   - speed
   - filename/file-index/file-size
   - servent-id // peers own id, but not really used since ip:port helps in establishing unique id
  - Say, the search started with peer 1 -> peer 2 -> peer 3. Peer 3 responded with Query hit, then peer2 will just forward the msg, unaltered back to peer 1. 
  - After receiving query-hit msgs
   - Requester chooses "best" QueryHit responder
   - Initiates HTTP request directly to the IP:port
    - GET /get/<File index>/<File Name>/HTTP/1.0\r\n
    - Connection: Keep Alive\r\n
    - Range: bytes=0-\r\n    // which range bytes do we want? say file is 2MB, we might request 0-512KB etc..
    - User agent: Gnutella\r\n
    - \r\n
   - Responder then replies with file packets after this msg
    - HTTP 200 OK\r\n
    - Server: Gnutella\r\n
    - Content-type: application/binary\r\n
    - Content-length: 1024\r\n
    - \r\n
   - Gnutella developers stick to HTTP since it a standard, well debugged, widely used - they didn't want to reinvent the wheel
   - The "range" field in GET request - To support partial file transfers
*   - What if responder is behind firewall that disallows incoming connections?
    - That's where the PUSH msgs come into picture
    - The overlay already has TCP connections between peers
    - It starts with the requester trying to make an HTTP request with the peer that responded with QueryHit
    - If that fails, requester sends PUSH to responder asking for file transfer.
    - The responser recvs it via the overlay and creates a TCP connection with the requester  
 - PUSH msg (0x40)
  - servent id, fileIndex // same as in recvd QueryHit
  - ip:port    // address where requester can accept incoming connections
  - Responder establishes  a TCP connection at ip:port & sends:
   - GIV <file index>:<servent id>/<file name>\n\n
  - Requestor then sends a GET msg to responder (as before) and file is transferred
*  - What if the requestor too is behind a firewall?
   - Gnutella gives up
   - alternative soln?   
 - PING/PONG msgs (no payload)
  - used by peers to update their neighbor list
  - PING msg is flooded out, TTL restricted and done periodically by every peer
  - PINGs are flooded out like Query's, PONG's routed along reverse path like Query hits
  - PONG replies used to update set of neighboring peers // to keep neighbor list fresh inspite of peers joining, leaving & failing
  - PONG msg contains:
   - ip:port of peer
   - no of files it has shared
   - size (KB/MB) of files
   
   
Gnutella summary:
 - No servers
 - Peers/servers maintain "neighbors", this forms an overlay graph
 - peers store their own files
 - Queries flooded out, TTL restricted
 - QueryHit, reverse path routed
 - Supports file transfer through firewalls
 - Periodic ping-pong to continuously refresh neighbor lists
  - List size specified by user at peer: heterogeneity means some peers have more neighbors
  - Gnutella found to follow POWER LAW distribution // the no of links a peer has follow POWER LAW distribution
   P(#links=L) ~ L^-k (k is a constant)
   
Problems:
 - ping/pong constituted 50% traffic
  - Sol: multiplex, cache & reduce freq of ping/pongs
 - repeated searches with same key word
  - sol: cache query, queryHit msgs
 - Modem connected host do not have enough BW for passing gnutella traffic
  - sol: use a central server to act as a proxy for such peers
  - sol: Fast track system
 - Large no of FREELOADERS // only download, never upload
  - 70% users in 2000 
 - Flooding causes excessive traffic
  - Is there some way of maintaining meta-information about peers that leads to more intelligent routing?
   - Structured p2p systems -> eg chord systems (upcoming in course) 
   
   
----------
Lecture 4:  Fast track & BitTorrent
----------
   
FAST-TRACK:
 - Hybrid between Gnutella & Napster
 - Takes advantage of healthier participants in the system
 - Underlying technology in Kazaa, kazaalite, grokster
 - proprietary protocol, but some details availble
 - Like Gnutella, but with some peers designated as SUPERNODES
  - super nodes stores a dir listing a subset of nearby (<file name, peer pointer>), similar to Napster server
  - super node membership changes overtime
  - Any peer can become & stay a supernode, provided it has earned enough REPUTATION:
   - Kazaalite: participation level (=reputation) of a user between 0 & 1000, initially 10, then affected by length of period of connectivity & total# of uploads
   - More sophisticated Reputation schemes invented, esp based on economics (p2pEcon workshop)
  - A peer searches by contacting a nearby supernode // results in low n/w traffic 
  
BIT-TORRENT:
 - Incentivises peers to participate in the system
 - Notion of TRACKERS - one tracker per file
 - peer wanting to join finds the tracker - '.torrent' file
 - tracker maintains a list of some of the peers currently transferring that file // it does so by recving HB from peers
 - peers are of 2 types:
  - seeds // have full file
  - leecher // have some blobs from the file
 - file is typically split into blobs of 32KB-256KB
 - 'download LOCAL RAREST FIRST block' policy: prefer early to download of blocks that are least replicated among neighbors
  - Exception: new node allowed to pick one random neighbor, helps in bootstrapping
 - Tit-for-tat BW usage: provide blocks to neighbors that provided it the best download rates
  - Incentives for nodes to provide good download rates
  - seeds do the same
 - Choking: Limit # of nighbors to which concurrent uploads <= x (say x=5) ie the best neighbors
  - everyone else choked
  - periodically re-evaluate it (eg 10 sec)
  - Optimistic unchoke: periodically (eg 30 sec), unchoke a random neighbor // helps keep unchoked set fresh
   

----------
Lecture 5:  Chord // Industrially deployed p2p solution
----------
  
  
DHT - Distributed Hash Table  
 - A hash table allows u to insert/lookup/delete objects with keys - in O(1)
 - A DISTRIBUTED hash table allow u to do the same in a distributed setting (objects=files)
  - instead of storing objects in bucks, it stores files on different nodes
 - Performance concerns:
  - Load balancing    // of nodes
  - Fault tolerance    // node failures
  - Efficiency of lookups/inserts
  - Locality      // msgs preferably t/f between near nodes
 - Napster, Gnutella, FastTrack are all DHTs (of sort)
 - So, is chord, a structured p2p system 



Comparative performance:

 ------------------------------------------------------------------
 Implementation  Memory     Lookup Latency     #msgs for lookup
 ------------------------------------------------------------------
 Napster   O(1) @client  O(1)    O(1)
     O(N) @server
    
 Gnutella  O(N)    O(N)    O(N)

 Chord   O(logN)    O(logN)    O(logN)
 ------------------------------------------------------------------
 In Napster, server stores info about all N clients


Chord:
 - Developed at Berkley & MIT
 - Intelligent choice of neighbhors to reduce latency and message cost of routing (look/inserts)
  - Gnutella decides neighbors on the basis on no of bytes shared
 - Uses consistent hashing on node's (peer's) address
  - SHA-1 (ip:port) -> 160 bit string // key-value
   - SHA-1 - Secure Hash Algorithm, a well known hashing algorithm.
   - O/p here is 160 bit string
  - Truncated to m bits
   - called peer-id (number b/w 0 - (2^m -1) )
  - Not unique but id conflicts very unlikely
  - can then map peers to one of the 2^m logical points on the circle
 - Peer pointer 
  - SUCCESSOR:
   - Every nodes keeps pointer of the next element (when put on a circle) 
   - can directly send msg to it
  - FINGER TABLES:
   - ith entry at peer with id n is 1st peer with id >= (n + 2^i) mod 2^m
   - For eg, for n=80, m=7, ith peer, 2^m = 128
    - 0th = (80 + 2^0) mod 128 = 80 + 1 = 81 (since next peer id>81 = 96, the 0th finger table entry will be 96)
    - 1st = (80 + 2^1) mod 128 = 80 + 2 = 82 (since next peer id>82 = 96, the 0th finger table entry will be 96)
    - 2nd = (80 + 2^2) mod 128 = 80 + 4 = 86    (since next peer id>84 = 96, the 0th finger table entry will be 96)
    
    
What about the files?
 - So that's how we place nodes on the ring, but how we place the files?
 - File names also hashed using same consistent hash function
  - SHA-1(filename) -> 160 bit string (key)
  - File is stored at 1st peer with id >= its key(mod 2^m)
   - eg File cnn.com/index.html that maps to key K42 is stored at 1st peer with id > 42
   - Note that we are considering a different file-sharing application here:  COOPERATIVE WEB CACHING // where client browsers at different nodes share search results with each other
   - The same discussion applies to any other file sharing application, including that of mp3 files
  - CONSISTENT HASHING => with K keys & N peers, each peer stores O(K/N) keys (ie <c.K/N, for some constant c) 
  
Search:
 - say, given points (nodes) on circle - N16, N32, N45, N80, N96, N112 & m =7
 - Now say N80 wants to search for 'cnn.com/index.html', it 1st hashes it (say to K42)
 - the next node near to 42 is N45 has the file
 - If it doesn;t have the file, then N80 fwds (via RPC) its request to its successor ie N96 which does the same thing
  - this helps if say N80 had some error in its finger table entry, N96 will save it.
  
Analysis:
 - The search algo takes O(logN) time
  // assuming successor and finger table entry info is correct/updated
 - Proof:
  - (intuition): at each step, distance between query & peer-with-file reduces by a factor of at least 2
  
Failures in chord:
 - Peers fail
  - Search under peer failures
   - Sol: maintain r multiple successors entries. In case of failure, use successor entries.
   - choosing r=2log(N) suffices to maintain lookup correctness (ie ring remains connected) with high probability
  - what if the node holding the copy fails?
   - sol: replicate the files @1 successor & predecessors
   - this helps in load balancing as well, since multiple nodes can now fulfill the request 
 - Peers join/leave
  - this is aka CHURN
  - p2p systems have high rate of churn
   - 25% per hour in Overnet (eDonkey)
   - 100% per hour in Gnutella
   - Lower in managed clusters
   - common feature in all distributed systems, incl wide area (eg planet lab), clusters ( eg Emulab), clouds (AWS) etc
 - So, all the time update successors & fingers & copy keys


New peers joining:
 - say, queue is N16, N32, N45, N80, N96, N112 & N40 is the new node to join
 - N40 contacts the introducer of the group (remember from assignment ?)
 - introducer redirects it to N45 (& N32)
 - N32 updates successor to N40
 - N40 initializes successor to N45, and inits fingers from it
 - N40 periodically talks to neighbors to update finger table // a stablization runs at each node, asking neighbor nodes for their finger tables to correct its table
 - Some of the keys of N45 needs to be copied over to N40 (for eg K34, K38 ie file IDs between K32 to K40, since next greater node for these keys is N40, not N45 anymore)
 - For dealing with failures we need Failure Detectors (Heartbeats, Gossip, SWIM etc)
 
Stabilization Protocol:
//Concurrent peer joins, leaves, failures might cause loopiness of pointers, & failures of lookups
 - Chord peers periodically run a stabilization algorithm that checks and updates pointers & keys
 - Ensures non-loopiness of fingers, eventual success of loopkups & O(logN) lookups with high probability
 - Each stabilization round at a peer involves a constant number of messages
 - strong stability takes O(N^2) stabilization rounds
 
 
Churn
 - could be very high, nodes constantly joining/leaving
 - significant effect to consider
  - Eg, traces from Overnet system show hourly peer turnover rates (churn) could be 25-100% of total number of nodes in the system
 - Leads to excessive (unneccessary) key copying (remember keys are replicated)
 - Stabilization algorithm may need to consume more BW to keepup
 - Main issue is that files are replicated, while it might be sufficient to replicate only meta information about files
 - Alternatives
  - Introduce a level of indirection (any p2p system)
  - replicate metadata more eg Kelips
  
  
Virtual nodes:
 // technique used by Chord for load-balancing
 - DHT Hashing can get non-uniform, which can lead to bad load balancing
 - Treat each node as multiple virtual nodes behaving independently, ie instead of calling giving it just one ID (say N1), give it mutiple IDs (N12, N13, N14 etc)
 - Each joins the system
 - Reduces variance of load balancing
 
Wrap-up Notes:
 - Virtual ring & consistent hashing used in Cassandra (facebook->Apache), Riak (Basho Technologies), Voldemort (linkedIn), DynamoDB (amazon) & other key-value stores
 - Current status of Chord project
  - File system (CFS, Ivy) built on top of chord
  - DNS lookup service built on top of chord
  - Internet Indirection Infrastructure (I3) project at UC Berkley
  - Spawned research on many interesting issues about p2p systems
  
  
----------
Lecture 7:  Pastry // p2p system born out of academia // prefix matching routing
----------

 - Designed by Anthony Rowstron (Microsoft Research) & Peter Druschel (Rice University)
 - Assigns Ids to nodes (using consistent Hashing function), just like Chord (using a virtual ring)
 - LEAF SET - Each node knows its successor(s) & predecessor(s)
 - Routing tables (for eg used by chord to determine neighbors - (n +2^i) mode 2^m ), instead here it uses prefix matching
  - Think of hyper cube
 - Thus routing is based on pre-fix matching , and is thus log(N)
  - And hops (neighbor edges) are short (in the underlying n/w)
  
Pastry Routing:
 - Consider a peer with ID 01110100101
 - It maintains a neighbor peer with an id matching each of the following prefixes (*=starting bit differing from this peer's corresponding bit) :
  - *
  - 0*
  - 01*
  - 011*
  - ... 0111010010*
 - When it needs to route to a peer, say 011101 _1_ 1001, it starts by forwarding to a neighbor with the LARGEST MATCHING PREFIX, ie 011101*
  - This results in O(logN) routing time
  
Pastry Locality:
 - For each prefix, say 011*, among all potential neighbors with a matching prefix, the neighbor with the shortest RTT (round trip time) is selected.
 - Since shorter prefix have many more candidates (spread out throughout the internet), the neighbors for shorter prefixes are likely to be closer than the neighbors for longer prefixes
 - Thus in prefix routing, early hops are short & later hops are longer
 - yet overall "stretch", compared to direct Internet path, stays short
 
Summary of Chord & Pastry
 - More structured than Gnutella
 - Black Box lookup algorithms
 - Churn handling can get complex
 - O(logN) memory & lookup cost
  - O(logN) lookup hops may be high
  - can we reduce the # of hops? 
  

----------
Lecture 8:  Kelips
----------
// constant lookup costs to DHT
// It is a 1 hop Lookup DHT

 - it doesn't use a virtual ring, instead uses K Affinity Groups -  K ~ sq root (N) // N=no of peers in the system
 - Each node hashed to a group (hash mod K)
 - Node's neighbors
  - almost all other nodes in its affinity group
  - one contact node per foreign affinity group
  
Kelips files & metadata
 - File can be stored at any (few) node(s)
 - Decouple file replication/location (outside Kelips) from file querying (in Kelips)
 - Each filename hashed to a group
  - All nodes in the group replicate pointer information ie <filename>, <file location = ip:port>
  - affinity groups does not store files
  
Kelips lookups:
 - Find file affinity group
 - go to your contact for the affinity group
 - failing that try another of your neighbors to find a contact
 - Lookup = 1 hop or a few // memory cost O(sq root N)
  - 1.93 MB for 100 K nodes, 10M files
  - Fits in RAM of most workstations/laptops today (COTS machine)  // COTS = Components Off The Shelf
  
Kelips soft state:
 // how do you update the neighbors
 - Member lists
  - Gossip based membership
  - within each affinity group
  - and also across affinity groups
  - O(logN) dissemination time
 - File metadata
  - Needs to be periodically refreshed from source node
  - Times out 
  
Chord Vs Pastry Vs Kelips
 - Range off trade off availble
  - Memory Vs lookup costs Vs background BW (to keep neighbors fresh)
  - Kelips uses more BW, but look up time is constant. Memory is sq root (N)
  
What we have studied:
 - Widely deployed p2p systems:
  - Napster
  - Gnutella
  - FastTrack (kazaa, kazaalite, grokster) // proprietary
  - Bit torrent
 - p2p systems with provable properties:
  - Chord // mit
  - Pastry // rice, MSFT
  - Kellips // cornel
  
  
  
======
WEEK 4
======
  
  
----------
Lecture 1.1:  Why Key/Value NoSQL ?
----------

The key-value abstraction:
 - Business (key) -> Value
 - (twitter.com) tweet id -> information about tweet
 - (amazon.com) item no -> information about it
 - (kayak.com) flight no -> information about flight eg availability
 - (yourbank.com) a/c no -> information about it

What is it? 
 - It is dictionary data structure
  - Insert, lookup, delete by a key
  - eg hash table, binary tree
 - But distributed
 - Sounds familiar - remember DHT (distributed hash tables) in p2p systems ?
 - It's not surprising that key value stores reuse many techniques from DHT 
 
isn't that just a Database?
 - Yes, sort of
 - Relational Database Management Systems (DBMSs) have been around for ages
 - MySQL is the most popular among them
 - Data stored in tables
 - Schema-based ie structured tables
 - Each row (data item) in a table has primary key that is unique within that table
 - Queried using SQL (Structured Query Language)
 - Supports join
 
Why MySQL doesn't work here?
 - Mismatch with today's workloads
  - Data: large & unstructured // not every entry has same no of columns
  - lots of random reads & writes
  - Sometime write-heavy   // relational DBs are optimized for read-heavy loads
  - Foreign keys rarely needed
  - Joins infrequent 
  
Needs of today's workloads:
 - Speed         // eg netflix system has to be fast
 - Avoid Single Point of Failure (SPoF) 
 - Low TCO (total cost of operation)  // for provider companies like twitter/netflix
 - Fewer system administrators
 - Incremental scalability
 - Scale out, not up - what ?
  - Scale up - grow your cluster capacity by replacing with more powerful machines  // high memory, faster CPU
   - Traditional approach
   - Not cost effective, as you're buying above the sweet spot on the price curve
   - And you need to replace machines often
  - Scale out - incrementally grow your cluster capacity by adding more COTS machines
   - cheaper
   - Over a long duration, phase in a few newer (faster) machines as you phase out a few older machines
   - Used by most companies who run datacenters & clouds today
   
   
Key-Value/NoSQL data model
 - NoSQL = 'Not Only SQL'
 - Necessary API operations: get(key) & put (key, value)
  - get returns value, put updates value if already exists
  - And some extended operations, eg "CQL" in Cassandra key-value store
 - Tables
  - "Column families"  in Cassandra, "Table" in HBase, "Collection" in MongoDB
  - Like RDBMS tables but,..
   - May be unstructured: May not have schemas
    - some columns may be missing from some rows
   - Don't always support JOIN or have foreign keys
   - Can have index tables, just like RDBMSs
   

Column oriented storage
// NoSQL systems often use column-oriented storage
 - RDBMSs store an entire row together (on disk or at a server)
 - NoSQL systems typically store a column together (or a group of columns)
  - Entries within the column are indexed and easy to locate, given a key (& vice versa)
 - Why useful?
  - Range searches within a column are fast since you don't need to fetch the entire database
  - eg get me all the blog_ids from the blog table that were updated within the past month
   - Search in the last_updated column, fetch corresponding blog_id column
   - Don't need to fetch the other columns
   

----------
Lecture 1.2:  Cassandra
----------

Cassandra
 - A distributed key-value store
 - Intended to run in a datacenter - DC (and also across DCs)
 - Originally designed at Facebook
 - Open sourced later, today an Apache project
 - Some of the companies that use Cassandra in their production clusters
  - IBM, adobe, HP, eBay, Ericsson, Symantec
  - Twitter, Spotify
  - PBS kids
  - Netflix: uses Cassandra to keep track of your current position in the video you're watching
  
   
   
Inside Cassandra: 
 - How do you decide which server(s) a key-value resides on?
  - Cassandra uses a Ring-based DHT but without finger tables or routing tables
  - Each ring is a DC
  - Client contacts the "co-ordinator"
  - Say, file with key = K13 will be stored in N16 (primary replica) & N32 & N45 (back up replicas)
  - Key -> server mapping is the "Partitioner"
  
Data Placement Strategies:
 - Replication Strategies
 
  1. SimpleStrategy    - uses the Partitioner, of which there are two kinds
   a. Randome Partitioner   - Chord like hash partitioning
   b. Byte order Partitioning - Assigns range of keys to servers
    - Easier for range queries (eg get me all the twitter users starting with [a-b])
    - If it was used on a hash-based partioning system, then for such range queries, it'll ask for server location for each key
    
  2. NetworkTopologyStrategy  - for multi-DC deployments
   - Two replicas per DC
   - Three replican per DC
   - per DC
    - First replica placed according to partitioner
    - Then go clockwise around ring untill you hit a different rack
    
Snitches:
 - Maps - IPs to rack & DC. Configured in cassandra.yaml config file 
 - Some options:
  - SimpleSnitch: Unaware of topology (rack unaware)
  - RackInferring: Assumes topology of n/w by octet of server's IP address
   - 101.102.103.104 = x.<DC outlet>.<rack octet>.<node octet>
  - Property File Snitch: uses a config file // if already knows topo, mention it in this file
  - EC2 Snitch: uses EC2
   - EC2 Region = DC #
   - Available zone = rack #
  - Other snitch options availble 
  
Writes:
 - Need to be lock-free and fast (no reads or disk seeks)
 - client sends write to one coordinator node in Cassandra cluster
  - Coordinator may be per-key, or per client, or per-query
  - per key coordinates ensures writes for the key are serialized
 - Coordinator uses Partitioner to send query to all replica nodes responsible for key
 - When X replicas repsond, coordinator returns an ACK to the client
  - X? Later.
  
 - Always writable: "Hinted handoff mechanism"
  - If any replica is down, the coordinator writes to all other replicas, and keeps the write locally until down replica comes back
  - When all replicas are down, the coordinator (front end) buffers writes (for upto few hours)
  
 - One ring per DC
  - Per-DC coordinator elected to coordinate with other DCs
  - Election done via ZooKeeper, which runs a Paxos (consensus) variant
   - Paxos - later in the course
   
   
 - Writes at a replica node  
  - On recving a write
  1. Log it in disk commit log (for failure recovery)
  2. Make changes to appropriate memtables
   - Memtable - In-memory representation of multiple key-value pairs
    //some latest key-value pairs that have been written to this node
   - cache that can be searched by a key
   - Write back cache as opposed to write-through
  Later, when memtable is full or old, flush to disk
   - Data file: An SSTable (Sorted String Table) - list of key-value pairs, sorted by key
   - Index file: An SSTable of (key, position in data sstable) pairs
   - And a bloom filter (for efficient search) - a quick way to check whether a particular key is present in the table or not
    - Compact way of resresenting a set of items
    - Checking for existence in a set is cheap
    - Some probability of false +ve, an item not in set may check true as being in set
    - Never false -ves
     - Say, 8 bit bloom-filter - 0 through 127 rows
     - it provides with some hashing functions hash_1-hash_k, each hash returns a no in range 0-127
     - Say, key K, hash1 is applied and returns value 1, set row 1 = TRUE
     - Say, key K, hash2 is applied and returns value 69, set row 1 = TRUE
     - Say, key K, hashK is applied and returns value 111, set row 1 = TRUE
     - do it with all the hash functions
     - with time, for other keys as well, apply all these hash functions and set corresponding bits to 1.
     - To check for presence of a key, apply all the hash functions to the key & check if all the location values are 1, if not, key isn't present in the bloomfilter
     - False +ve are there, but are low.
     - For eg with k=4 ie 4 hash functions, 100 items, 3200 bits, False Positive rate = 0.02%


Compaction
 - Data updates accumulate over time & SStables and logs need to be compacted (on disk)
 - The process of compaction merges SSTables ie by merging updates for a key
 - Run periodically & locally at each server   
   

deletes:
 - Don't delete item right away
 - Add a tombstone to the log   
 - Eventually, when compaction encounters tombstone it will delete item
   
   
   
Reads: // Similar to writes except
 - Coordinator can contact X replicas (eg in same rack)
  - Coordinator sends read to replicas that have responded quickest in past
  - When X replicas respond, coordinator returns the last-timestamped value from among those X (?)
 - Coordinator also fetches value from other replicas
  - Checks consistency in the background, initiating a read repair if any two values are different
  - This mechanism seeks to eventually bring all replicas up to date 
 - A row may be split across multiple SSTables => reads need to touch several SSTables => reads slower than writes (but still fast)
 
 
Membership
 - Any server in the cluster could be a coordinator
 - So every server needs to maintain a list of all the other servers that are currently in the server
 - List needs to be updated automatically as servers join, leave, fail. 
 
Cluster membership - Gossip style
 - Details in lecture 1 
 
Suspicion mechanisms in Cassandra
 - to adaptively set the timeout based on underlying network & failure behaviour
 - Accrual detector: Failure detector outputs a value (PHI) representing suspicion
 - Apps set an appropriate threshold
 - PHI calculation for a member
  - Inter-arrival times for gossip messages
  - PHI (t) = - log (CDF or probability(t_now - t_last))/log 10
  - PHI basically determines the detection timeout, but takes into a/c historical inter-arrival time variations for gossiped HBs
  - In practice, PHI = 5 => 10-15 sec detection time
  
Cassandra Vs RDBMS
 - MySQL is one of the most popular (and has been for a while)  
 - On > 50 GB
 - MySQL
  - Writes 300 ms avg
  - Reads 350 ms avg
 - Cassandra
  - Writes 0.12 ms avg
  - Reads 15 ms avg
 - Orders of magnitude faster
 - What's the catch? what did we lose?  
 
 
----------
Lecture 1.3: The Mystery of X - The CAP theorm
----------

The X in the read/writes in Cassandra... 

CAP theorm
 - In a distributed system you can satisfy  2 out of 3 guarantees
  1. Consistency    - all nodes see same data at any time, or reads return latest written value by a client
  2. Availability   - the system allows operations all the time, & operations return quickly
  3. partition-tolerance  - the system continues to work in spite of network partitions
  
Why is Availability important?
 - Availability = reads/writes completely reliable & quickly
 - measurements have shown that a 500 ms increase in latency for operations at amazon.com or at google.com can cause 20% drop in revenue
 - At amazon, each added ms of latency implies $6M yearly loss
 - SLAs (Service Level Agreements) written by providers  predominantly deal with latencies faced by clients
 
Why is Consistency important? 
 - Consistency = all nodes see same data at any time, or reads return latest written value by any client
 - When you access your bank or investment a/c via multiple clients (laptop, workstation, tablet, phone), you want to updates done via one client be visible through the other
 - When thousands of customers are looking to book a flight, all updates from any client (eg book a flight)  should be accessible by other clients


Why is partition-tolerance important?  
 - Partitions can happen across DCs when internet gets disconnected
  - Internet router outages
  - Under-sea cable cut
  - DNS not working
 - Partitions can also occur within a DC eg a rack switch outage
 - Still desire system to continue functioning normally under this scenario 
 
 
CAP theorem fallout
 - Since partition-tolerance is essential in today's cloud computing systems, CAP theorem implies that a system has to choose b/w consistency & availbilty
 - Cassandra    - eventual (weak) consistency, Availability & partition tolerance 
 - Traditional RDBMSs - Strong consistency over availability under partition
 
 
Eventual consistency
 - if all the writes a key stops, then all its replicas will converge eventually
 - if writes continues, the replicas will always try converging
 - May still return stale values to client s
 - But works well when there are a few periods of low writes  - system converges quickly 


RDBMS vs key-value stores
 - While RDBMS provides ACID
  - A - Atomicity
  - C - Consistency
  - I - Isolation
  - D - Durability
 - key-value stores like Cassandra provide BASE
  - Basically Available Soft-state Eventual consistency
  - Prefers availability over consistency
  
  
Now, back to the variable X in Cassandra ie the consistency level
 - Client is allowed to chose a CONSISTENCY LEVEL for each operation (read/write) 
  - ANY - any server (may not be replica)
   - Fastest - coordinator caches write & replies quickly to client
  - ALL - all replicas
   - Ensures strong consistency, but slowest
  - ONE - atleast one replica
   - Faster than ALL but cannot tolerate a failure
  - QUOROM - quorom (majority of atleast 50%) across all replicas in a DC
   - ie atleast 50% of the replicas have updates
   - dont have to wait for all replicas to return ACK
   - faster than ALL & still ensures strong consistency

Two neccessary conditions:   
 X = R for read &
 X = W for write
 1. W+R > N
 2. W > N/2
 - Select values based on application
  - W=1, R=1 for few writes/reads
  - W=N, R=1 for read heavy workloads
  - W=N/2+1, R=N/2+1  for write heavy workloads
  - W=1, R=N for write heavy workloads with mostly one client writing per key
  
Additional Cassandra consistency levels: 
 - QUORUM  - quorum across all replicas in all DC
  - global consistency but still fast
 - LOCAL_QUOROM - quorom in coordinator's DC
  - Faster - only waits for quorom in first DC client contacts
 - EACH_QUOROM - quorom in every DC
  - Lets each DC do its own quorum: supports hierarchical replies    
  
  

----------
Lecture 1.4: The consistency spectrum
----------
Range is from "Eventual" to "strong (eg sequential)" 
 - Towards "Eventual" -> faster reads & writes
 - Towards "strong"   -> More consistency
 
 
 
Newer consistency models (from weaker at top to strongest at bottom):
 - Eventual
 - Causal
 - Per-key sequential
   - Per key, all ops have a global order
 - Red-blue
   - rewrite client transactions to separate ops into red ops Vs blue ops
   - blue ops can be executed (commutated) in any order across DCs
   - red ops  need to be executed in the same order
 - Probabilistic
 - CRDTs (Commutative Replicated Data Types)
   - commutated writes - reverse the ops of writes, the result will be the same
   - CRDTs are DS for which commutated writes give same results
   - for eg a z++ operation is commutative
   - servers don't need to worry about ordering of writes
   - red-blue (above) is an improvement to it
 - Strong 
 


Strong consistency models
 - Linearizability    - Each ops by a client is visible (or available) instantaneously (real time) to all other clients
 - Sequential consistency - ".. the result of any exection is the same as if the operations of all the processors were executed in some sequential order, and the ops of each individual processors appear in this sequence in the order specified by its program"
        - After the fact, find a resonable ordering of operations (can re-order ops) that obeys sanity (consistency) at all clients, and across clients
 - Transaction ACID properties eg, newer key-value/NoSQL stores (sometimes called  "NewSQL") 
        - Hyperdex [cornell]
        - Spanner [google]
        - Transaction chains [MSFT research]
        
        
        
----------
Lecture 1.5: HBase
----------
 - Google's Bigtable was 1st "blob-based" storage system
 - Yahoo! open sourced it -> HBase
 - Major Apache project today
 - Facebook uses HBase internally
 - API functions
  - get/put (row)
  - scan (row range, filter) - range queries
  - multiput
 - Unlike Cassandra, HBase prefers consistency (over availability) 
        
        
        
HBase architecture:
 - HBase table
  - split into mutiple regions, replicated across servers
   - Column family - subset of col with similar query pattern
   - one STORE per combination of COL_FAMITLY + region
    - Memstores for each stores, in memory updates to store, flushed to disk when full
     - Store file - for each store for each region, where the data lives
      -> HFile
 - HFile 
  - SSTable from Google's BigTable
  
  
Strong consistency: HBase write-ahead log
 - Write to HLog before writing to MemStore
 - helps recover from failure by replaying HLog  
 
 
Cross DC replication
 - Single "Master" cluster
 - Other "slave" clusters replicate the same tables
 - Master cluster synchronously sends HLogs over to slave clusters
 - Coordination among clusters is via Zookeeper
 - ZooKeeper can be used like a file system to store control information
  1. /hbase/replication/state
  2. /hbase/replication/peers/<peer cluster number>
  3. /hbase/replication/rs/<hlog>     
  
  
Summary
 - Traditional databases (RDBMSs) work with strong consistency, and offer ACID
 - Modern workloads don't need such strong guarantees, but do need fast response times (availability)
 - Unfortunately, CAP theorem
 - Key-value/No-SQL systems offer BASE
  - Eventual consistency, and a variety of other consistency models striving towards strong consistency
 - Discussed designs of
  - Cassandra  // prefers availability over consistency
  - HBase   // prefers consistency over availability
  
  
----------
Lecture 2.1: Time & ordering, Introduction & basics
----------
  
  
Time synchronization is red for:
 - Correctness
 - Fairness
 
Sync in the cloud:
 - Say booking a flight
 - last ticket of flight
 - server A replies to client, logs the time 9 hr 15 min
 - ANother person tries to book, but server B says flight is full & logs its local time as 9 hr 10 min
 - Server C checks A & B logs and is confused that a client purchased a ticket at A  after the flight became full a B
 - This may lead C to take further incorrect actions
 - If A & B has same time, this issue would not have arised
 
Why is it challenging?
 - End hosts in internet systems hae their own clock
 - Processes in Internet based systems follow an asynchronous system model
  - No bounds on message delays & processing delays
  - Unlike multi-processor systems which follow synchronous system model
  
Some definitions:
 - An asynch distributed system consists of no of processes
 - Each process has a state
 - Process takes action to change its state, which may be an instruction or communication (send/recv)
 - An event is occurence of an action
 - Each process has a local clock - events within a process can be assigned timestamps & thus ordered linearly
 - But in distributed system, we also need to know the time order of events across different processes
 
 
Clock skew Vs clock Drift
 - Each process running on some end node and its own clock
 - When comparing two clocks at two processes
  - Clock skew - Relative difference in clock value of two processes
   - Like distance between two vehicles on road
  - Clock drift - Relative difference in clock frequencies of two processes
   - Like difference in speed of two vehicles on road
 - A non 0 clock skew means clocks aren't sync
 - A non 0 clock drift causes skew to increase (eventually)    
  - If the faster vehicle is ahead, it will drift away
  - If the faster vehicle is behind, it will catch up and then drift away
  
How often to synch clocks of processes?
 - Max Drift Rate (MDR) of a clock
 - Absolute MDR is defined relative Coordinated Universal Time (UTC). UTC is the correct time at any point in time
  - MDR of a process depends on the environment
 - Max drift rate between two clocks with similar MDR is 2 x MDR
 - Given a max acceptable skew 'M' between any pair of clocks, need to synch at least once every M/2xMDR time units
  - Since time = distance/speed 
  
External Vs Internal Synch
 - Consider a group of processes
 - External sync
  - Each process C(i)'s clock is within a bound D of a well known clock S external to the group
  - ie | C(i) - S | < D at all times
  - External clocks may be connected to UTC or an atomic clock, eg Christians algorithm - NTP
 - Internal Sync
  - Every pair of processes in group have clocks within bound D
  - ie | C(i) - C(j) | < D at all times for all processes i, j
  - Eg Berkeley algorithm
  
Relation between Internal/External sync
 - External synch with D => Internal synch with 2xD
 - Internal synch doesn't imply External synch
  - In fact entire system may drift away from external clock S !  
  
  
----------
Lecture 2.2: Christian's algorithm (external clock synch)
----------
  
All the processes P synch with an external server S
Problem with this approach:
 - By the time server msg is recvd at P, time has moved
 - P's time set to t is inaccurate
 - Inaccuracy is a function of message latency here
 - Since latency is unbounded in an asynch system, the inaccuracy can not be bounded
 
Christian's algo
 - measure the RTT of the msg
 - we know  // P = processe, S = server
  - minimun P -> S latency - say min_1
  - minimun S -> P latency - say min_2
  - min_1 & min_2 depend upon OS system overhead to buffer msgs, TCP time to queue msgs etc
 - So the actual time at P when it recvs the msg = [t + min_2, t + RTT - min_1] 
 - So, set the time half way of the two, ie t' = t  + (min_2 + RTT - min_1)/2
  - Error is atmost (RTT-min_2-min_1)/2 - BOUNDED !
  
Gotchas
 - Allowed to increase clock value but should never decrease clock value
  - May violate ordering of events within the same process
 - Allowed to increase/decrease speed of clock
 - If error is too high, take multiple readings and avg them   
 
 
----------
Lecture 2.3: NTP // standard from internet
----------
Network Time Protocol 
 - NTP servers organised in a tree (primary server, secondary server, tertiary server)
 - Each client is a leaf of tree
 - Each node synch with its tree parent 
 
Working:
 - Child sends a msg to parent saying I want to synch my clock
 - Parent replies, stamps its local time - ts1
 - child recvs, stamps its local time - tr1
 - child sends its local time ts2
 - parent recvs - tr2
 - server sends ts1, tr2 to child
 - client use tr1, tr2, ts1, ts2 to sets its clock offset , o = (tr1-tr2 + ts2-ts1)
 - lets calculate the error
 - suppose the real offset is oreal
  - child is ahead by parent by oreal
  - parent is ahead of child by -oreal
 - suppose one-way latency of message M1 is L1 (L2 for msg 2) 
 - No one known L1 or L2
  - then, tr1 = ts1 + L1 + oreal
  -   tr2 = ts2 + L2 - oreal
  - | oreal - o | < |(L2-L1)/2| < |(L2+L1)/2|
 - Still we have a non-zero error! // due to msg latencies
 - need to find a way to order events without synch clocks 
 
----------
Lecture 2.4: lamport timestamps
----------
Ordering events in a distributed systems
 - To order events across processes, trying to synch clocks is one approach
 - what if we assign timestamps to processes that aren't absolute time
 - As long as these timestamps obey causality, that would work
  - If an event A causally happens before another event B, then timestamp(A) < timestamp (B)
  
Logical (or Lamport) ordering
 - Proposed by Leslie Lamport in 1970s
 - Used in all distributed systems since then
 - almost all cloud computing systems use some form of logical ordering of events
 - Three rules
  - On the same process, a -> b, if time (a) < time (b) (using local clock)
  - If p1 sends m to p2, send(m) -> recv(m)
  - (Transitivity) If a -> b & b->c then a->c
  - creates a partial order among events
   - not all related to each other via '->'
   
In practice: Lamport Timestamps
 - Goals: Assign logical (Lamport timestamps) to each event
 - Timestamps obey causality
 - Rules
  - Each process uses a local counter (clock) which is an integer
   - Initial value of counter is zero
  - A process increments its counter when a send or an instruction happens at it. The counter is assigned to the event as its timestamp 
  - A send (msg) carries its timestamp
  - For a recv (msg) event the counter is updated by max(local clock, message timestamp) + 1
  - Concurrent events pairs - events a/c processes that don't have any causality
   - doesn't have a  causal path from one event to another (either way, in the pair)
   - Lamport timestamps not guaranteed to be ordered or unequal for concurrent events
   - that's fine since concurrent events are not causally related
   - E1 -> E2       => timestamp(E1) < timestamp(E2), BUT
   - timestamp(E1) < timestamp(E2) => {E1 -> E2} or {E1 & E2 concurrent}
   
   
----------
Lecture 2.5: Vector Clocks
----------
   
- Used in key-value stores like Riak
- Each process used a vector of integer clocks
- Suppose there are N processes in group 1...N
- Each vector has N elements
- Process i maintains vector Vi[1..N]  
- jth element of vector clock at process i, Vi[j], is i's knowledge of latest events at process j

Incrementing vector clocks:
 - On an instruction, send at process i, Vi[i]++
 - Each msg carries the send-event 's vector timestamp Vmessage[1..N]
 - on recving a msg at process i
  - Vi[i] = Vi[i] + 1  // increment its own clock
  - Vi[j] = max(Vmessage[j], Vi[j]) for j!= i
  
Causally related:
 - VT1 = VT2 // VT = Vector Timestamp
  - iff VT1[i] = VT2[i] for all i=1..N
 - VT1 <= VT2 // VT = Vector Timestamp
  - iff VT1[i] <= VT2[i] for all i=1..N
 - Two events are causally related iff 
  - VT1[i] < VT2[i] i.e.
  - iff VT1[i] <= VT2[i] & there exists j (1<=j<=N) such that
   - VT1[j] < VT2[j]
Concurrent events:
 - Two event VT1 & VT2 are concurrent iff
  - NOT (VT1 <= VT2) AND NOT (VT2 <= VT1)
  - we'll denote this as VT2 ||| VT1
  
Logical Timestamps Summary:
 - Lamport Timestamps
  - Integer clocks assigned to events
  - Obeys causality
  - cannot distinguish concurrent events
 - Vector timestamps
  - obey causality
  - By using more space, can also identify concurrent events   
  
  
======
WEEK 5
======

----------
Lecture 1.1: What is Global Snapshot ?
----------

In the cloud:
 - Cloud applications running on multiple servers
 - servers handling concurrent events & interacting with each other
 - The ability to obtain a "global photograph" of the system is important
 - Some uses of having a global picture of the system
  - Checkpointing: can restart distributed applications on failure
  - Garbage collection of objects: objects at servers that don't have any other objects (at any server) with pointers to them
  - Deadlock detection: Useful in database transaction systems
  - Termination of computation: Useful in batch computing systems like folding@home, SETI@home
  
  
Global snapshot=Global state
 - Individual state of each process in the distributed system
  +
 - Individual state of each communication channel in the distributed system
  - Capture the instantaneous state of each process  
  - And the instantaneous state of each communication channel ie messages in transit on the channels
 
 
Obvious first solution:
 - Synch clocks of all processes
 - Ask all processes to record their states at known time t
 - Problems ?
  - time synch always has error
   - Your bank might inform you, "we lost the state of our distributed cluster due to 1ms clock skew in our snapshot algorithm" 
   - Also doesn't record the state of messages in the channels
 - Again, synch is not required - causality is enough  


----------
Lecture 1.2: Global snapshot algorithm
----------
 
Sytem Model
 - Problem: Record a global snapshot (state for each process, and state for each channel) 
 - System model:
  - N processes in the system
  - There are two uni-directional communication channels between each ordered process pair:
   Pj -> Pi,
   Pi -> Pj
  - Communication channels are FIFO-ordered
  - No failure
  - All messages arrive intact, and are not duplicated
   - Other papers later relaxed some of these assumptions 
   
Requirements:
 - Snapshot should not interfere with normal application actions, and it should not require application to stop sending messages
 - Each process is able to record its own state
  - Process state: Application defined state or in the worst case,
  - its heap, program counter, code etc (essentially the core dump)   
  - global state is collected in a distributed manner
  - Any process may initiate the snapshot
   - We'll assume just one snapshot run for now
   

   
Chandy-Lamport global snapshot algorithm
// Causally correct
 - First, initiator Pi records its own state
 - Initiator process creates special messages called "markers" messages
  - Not an application msg, does not interfere with application messages
 - for j=1 to N, except i
  - Pi sends out a Marker msg on outgoing channel Cij
  - N-1 channels
 - Starts recording the incoming msgs on each of the incoming channels at Pi: Cji (for j=1 to N except i)  
 - Whenever a process Pi receives a marker message on an incoming channel Cki
  - if (this is a 1st marker Pi is seeing)
   - Pi records its own state first
   - Marks the state of the channel Cki as empty
   - For j=1 to N except i
    - Pi sends out a marker msg outgoing channel Cij
   - Starts recording the incoming msg on each of the incoming channels at Pi: Cji (for j=1 to N, except i & k) 
  - else // already seen a marker
   - Mark the state of the channel Cki as all the msgs that have arrived on it since recording was turned on for Cki 
 - TERMINATION - the algo terminates when
  - All processes have received a Marker
   - To record their own state
  - All processes have received a Marker on all the (N-1) incoming channels at each
   - To record the state of all the channels
  - Then if needed, a central server collects all these partial state pieces to obtain the full global snapshot 
  
  
----------
Lecture 1.3: Consistent cuts
----------
  
  
Cuts:
 - Cut = time frontier at each process and at each channel
 - Events at the process/channel that happen before the cut are "in the cut"  
  - and happening after the cut are "out of the cut"
  
Consistent cuts:
 - a cut that obeys causality
 - A cut C is a consistent cut iff
  - for (each pair of events (e,f) in the system)
  - such that event e is in the cut, and if f->e (f happens before e)  
   - Then event f is also in the cut C
 - Any run of Chandy-Lamport global snapshot algorithm creates a consistent cut  
 
Proof:  - Any run of Chandy-Lamport global snapshot algorithm creates a consistent cut  
 - Let e_i & e_j be events occurring at Pi & Pj respectively such that
  - e_i -> e_j (e_i happens before e_j) 
 - The snapshot algo ensures that
  - if e_j is in the cut then e_i is also in the cut
 - ie if e_j -> <Pj records its state>, then
  - it must be true that e_i -> <Pi records its state>
  
 // lets prove the above two lines by contradiction
 -  suppose e_j -> <Pj records its state> & <Pi records its state> -> e_i // reverse order j->i
 - consider the path of app msgs (through other processes) that go from e_i -> e_j
 - Due to FIFO ordering, markers on each link in above path will precede regular app msgs
 - Thus, since <Pi records its state> -> e_i, it must be true that Pj recvd a marker before e_j
 - Thus e_j is not in the cut => contradiction
 
----------
Lecture 1.4: Safety and liveness
----------
 
Correctness in distributed systems
 - can be seen in two ways
 - liveness & safety
 - often confused - it's important to distinguish from each other 

Liveness:
 - guarantee that something good will happen eventually
  - eventually does not imply a time bound, but if you let the system run long enough, then..
 - examples in real world
  - guarantee that "atleast one of the atheletes in the 100m final will win gold" in liveness
  - a criminal will eventually be jailed
 - example in distributed system
  - distributed computation: guarantee that it will terminate
  - "completeness" in failure detectors: every failure is eventually detected by some non-faulty process
  - In consensus: all processes eventually decide on a value
  
  
Safety:
 - guarantee that something bad will never happen
 - examples in real world
  - A peace treaty between two nations provides safety // war will never happen
  - An innocent person will never be jailed
 - example in distributed system
  - There is no deadlock in a distributed transaction system
  - No object is orphaned in a distributed object system
  - "Accuracy" in failure detectors
  - In consensus: No two processes decide on different values
  
Can't we guarantee both liveness & safety?
 - Can be difficult to satisfy both in an asynch distributed system (DS)
  - Failure detector: Completeness (Liveness) & Accuracy (safety) cannot both be guaranteed by a FD in an asynch DS
  - Consensus: Decisions (Liveness) and correct decisions (safety) cannot both be guaranteed by any consensus protocol in an asynch DS
  - Very difficult for legal systems (anywhere in the world) to guarantee that all the criminals are jailed (liveness) & no innocents are jailed (safety)
  
  
In the language of global states
 - recall that a Distributed System moves from one global state to another, via causal steps
 - Liveness wrt a property Pr in  a given state S means
  - S  satisfies Pr, or there is some causal path of global states from S to S' where S' satisfies Pr
 - Safety wrt property Pr in a given state S means
  - S satisfies Pr, and all global states S' reachable from S also satisfy Pr  
  

Using global snapshot algo
 - Chandy-Lamport algorithm can be used to detect global properties that are stable
  -  stable = once true, stays true forever afterwards
 - stable liveness example
  - computation has terminated
 - stable non-safety example
  - there is a deadlock
  - an object is orphaned (no pointers point to it)    
 - all stable global properties can be detected using the Chandy-Lamport algo
  - due to its causal correctness 
  
  
Summary
 - the ability to calculate a global snapshot is very imp in a distributed system
 - but don't want to interrupt a running distributed application
 - Chandy-Lamport algo calculates global snapshot
 - obeys causality (creates a consistent cut)  
 - can be used to detect stable global properties
 - safety vs liveness
 
 
----------
Lecture 2.1: Multicast ordering
----------

Multicast problem
 - Send a msg to a group of processes
  - this is not a broadcast where msg is sent to everyone
  - this is not a unicast where msg is sent to just one process
 
 
Who uses multicast ?
 - A widely used abstraction by all cloud systems
 - storage system like cassandra or a DB
  - Replica servers for a key: writes/reads to a key are multicast within the replica group
  - All servers: membership information (eg HBs) is multicast across all servers in cluster
 - online scoreboard (ESPN, frech open, FIFA world cup) 
  - multicast to a group of clients interested in the scores
 - stock exchange
  - group is a set of broker computers
  - groups of computers for high frequency trading
 - Air traffic control system
  - all controllers need to receive the same updates in the same order  

Types of ordering   
 1. FIFO ordering
  - multicasts from each sender are received in the order they are sent, at all receivers
  - don't worry about multicasts from different senders 
  - More formally
   - if a process issues (sends) multicast(g,m) to group g and then multicast(g,m'), then every correct process that delivers m' would have already delivered m.
  
 2. Causal ordering:
  - multicasts whose send events are causally related, must be recvd in the same causality-obeying order at all receivers
  - Formally
   - If multicast(g,m) -> multicast(g,m') then any correct process that delivers m' would already have delivered m (-> is Lamport's happen's before)
  
  
 Causal Vs FIFO
  - Causal ordering => (implies) FIFO ordering
   - system that satisfies Causal ordering, will also satisfy FIFO ordering
   - WHY?
    - If two multicasts M & M' are sent by the same process P, and M was sent before M', then M -> M'
    - Then a multicast protocol that implements causal ordering will obey FIFO ordering since M -> M'
  - Reverse is not true, ie FIFO ordering does not imply causal ordering
 
 
 Why causal at all
  - group = set of your friends on a social network
  - A friend sees your message m, and she posts a response (comment) m' to it
   - If friends recv m' before m, it won't make sense
   - but if two friends post msgs m'' & n'' concurrently, then they can be seen in any order at receivers
  - A variety of systems implement causal ordering: social network, bulletin boards, comments of website etc  
  
 3. Total ordering
  - Aka atomic broadcast
  - Unlike FIFO and causal, this does not pay attention to order of multicast "sending"
  - Ensures all recvrs "receive" all multicasts in the same order
  - Formally
   - If a correct process P delivers message m before m' (independent of the sender), then any other correct process P' that delivers m' would already have delivered m
   // notice it doesn't talk about sender, only receivers. all the receivers see the same ordering.
   
 4. Hybrid Variants
  - Since FIFO/causal are orthogonal to total, can have hybrid ordering protocols too
  // causal/FIFO look at sending order, Total looks at recving order
   - FIFO-total hybrid protocol satisfies both FIFO & total orders
   - Causal-total hybrid protocol satisfies both causal and total orders  
   
Implementation ?
 - That was what ordering is
 - But how do we implement each of these orderings?


----------
Lecture 2.2: Implementing Multicast (FIFO & Total)
----------

FIFO multicast: Data structures
 - Each recvr maintains a per-sender sequence # (integers)
  - Process P1 through PN
  - Pi maintains a vector of sequence numbers Pi[1..N] (initially all 0s)
  - Pi[j] is latest sequence # Pi has recvd from Pj
  
FIFO multicast: updating rules
 - Send multicast at process Pj
  - set Pj[j] = Pj[j] +1
  - include new Pj[j] in multicast msg as its sequence #
 - recv multicast: If Pi recvs a multicast from Pj with sequence # S in message 
  - If (S==Pi[j]+1) then
   - deliver msg to the application
   - set Pi[j] = Pi[j] + 1
  - else buffer this multicast until above condition is true
  // if S < Pi[j]+1, to avoid duplicates, can drop this msg 
  
  
Total ordering: Sequence based approach
 - Special process selected as leader or sequencer
 - Send multicast at Pi
  - send multicast message M to group and sequencer
 - Sequencer
  - maintains a global sequence number S (initially 0)
  - when it recvs a multicast  message M, it sets S = S + 1, and multicasts<M,S>
 - Receive multicast at process Pi:
  - Pi maintains a local recvd global sequence number Si (initially 0)
  - If Pi recvs a multicast M from Pj, it buffers it untill it both
   1. Pi recvs <M, S(M)> from sequencer and
   2. Si + 1 = S(M)
    - then deliver msg to the app and set Si = Si + 1
----------
Lecture 2.3: Implementing Multicast (Causal)
----------
    
Causal Ordering: Data structures
 - Each recvr maintains  a vector of per-sender sequence # (integer)
  - Similar to FIFO multicast, but updating rules are different
  - Process P1 to PN
  - Pi maintains a vector Pi[1..N] (initially all 0)
  - Pi[j] is the lastest sequence # Pi has recvd from Pj


Causal multicast: updating rules
 - Send multicast at process Pj
  - Set Pj[j] = Pj[j] + 1
  - Include new entire vector Pj[1..N] in multicast msg as its sequence #
 - Recv multicast: If Pi recvs  a multicast from Pj with vector M[1..N] (=Pj[1..N]) in msg, buffer it until both:
  1. This msg is the next one Pi is expecting from Pj ie
   - M[j] = Pi[j] + 1
  2. All multicasts, anywhere in the group, which happened-before M have been recvd at Pi ie
   - For all k != j: M[k] <= Pi[k]
   - ie recvr satisfies causality
  3. when above two conditions satisfied, deliver M to application and set Pi[j] = M[j] 
  
  
  
----------
Lecture 2.4: Reliable multicast
----------
  
// reliable = every "correct" processes in the group recvs all multicast
// faulty processes are unpredictable, so we won't worry about them

Implementing reliable multicast
 - Lets assume we have a reliable unicast (eg tcp) available to us
 - First cut - sender process (of each multicast M) sequentially sends a reliable unicast msg to all group recipients
 - First-cut protocol doesn't satisfy reliability
  - if sender fails, some correct process might recv multicast M, while other processes might not recv M
  
REALLY implementing reliable multicast
// trick: have recvrs help the sender
 1. sender process (of each multicast M) sequentially sends a reliable unicast msg to all group recipients
 2. when a recvr receives a multicast M, it also sequentially sends M to all the group's processes
 
 
Analysis:
 - not the most efficient multicast protocol, but reliable
 - proof is by contradiction
 - Assume 2 correct processes Pi & Pj are so that Pi recvd a multicast M and Pj did not recv M
  - then Pi would have sequentially sent the multicast M to all group members, including Pj, and Pj would have recvd M
  - A contradiction
  - hence our initial assumption must be false
  - hence protocol preserves reliability


----------
Lecture 2.5: Virtual Synchrony
----------
// how to combine a membership protocol/ failure detection with a multicast

Virtual synchrony or View synchrony
 - Attempts to preserve multicast ordering & reliability inspite of failures
 - Combines a membership protocol with a multicast protocol
 - systems that implemented it (like isis) have been used in NYSE, french air traffic control system, swiss stock exchange
 
 
// imp concept
Views:
 - Each process maintains a membership list
 - the membership list is called a view
 - An update to the membership list is called "View change" 
  - process join/leave or failure
 - virtual synchrony guarantees that all "view changes are delivered in the same order at all correct processes"
  - if a correct process P1 receives views, say, {P1}, {P1, P2, P3}, {P1, P2}, {P1, P2, P4} then
  - any other correct process receives the same sequence of view changes (after it joins the group)
   - P2 recvs views {P1, P2, P3}. {P1, P2}, {P2, P2, P4}
 - views may be delivered at different physical times at processes, but they are delivered in the same order  
 
Vsync Multicasts
 - A multicast M is said to be "delivered in a view V at process Pi" if
  - Pi recvs view V, and then sometime before Pi recvs the next view it delivers multicast M
 - Virtual synchrony ensures that
  1. "The set of multicasts delivered in a given view is the same set at all correct processes that were in that view"
   - what happens in a view, stays in the view
  2. sender of the multicast msg also belongs to that view
  3. If a process Pi does not deliver a multicast M in view V while other processes in the view V delivered M in V, then Pi will be forcibly removed from the next view delivered after V at the other processes  
  
What about multicast ordering?
 - Again, orthogonal to virtual synchrony
 - the set of multicasts delivered in a view can be ordered either
  - FIFO
  - or causal
  - or totally
  - or using a hybrid scheme  
  
About that name
 - called "virtual synchrony" since in spite of running on an asynch n/w, it gives the appearance of a synch n/w underneath that obeys the same ordering at all processes
 - so can this virtual synchrony system be used to implement consensus?
 - No! VSynch groups susceptible to "partitioning"
  - for eg due to inaccurate failure detectors
  
Summay
 - multicast an imp  building block for cloud computing systems
 - depending upon the appl need, can implements
  - ordering
  - reliability
  - virtual synchrony
  
----------
Lecture 3.1: The consensus problem
----------
Give it a thought
 - distributed server vendors always only offer solutions that promise five-9's reliability, sever-9's reliability, but never 100% ?
 - the fault doesn't lie with the companies themselves, or worthlessness of humanity
  - the fault lies in the "impossibility of consensus"

What is common to all of these?
 - A grp of servers attempting
  - make sure that all of them recv the same  updates in the same order as each other
   - RELIABLE MUTLICAST
  - to keep their own local lists where they know about each other, and when anyone leaves or fails, everyone is updated simultaneously
   - MEMBERSHIP/FAILURE DETECTION
  - elect a leader among them, and let everyone in the group know about it
   - LEADER ELECTION
  - to ensure mutually exclusive (one process at a time only) access to a critical resource like a file
   - MUTUAL EXCLUSION
  
  
  
So, what is common?
 - lets call each server a process (think of daemon at each server)
 - all of these were groups of processes attempting to coordinate with each other & reach agreement on the value of something
  - the ordering of msgs
  - the up/down status of a suspected failed process
  - who the leader is
  - who has access to the critical resource
 - all of these are related to consensus problem   
 
What is consensus?
// formal problem statement
 - N processes
 - each process P has
  - input variable xp: initially either 0 or 1
  - output variable yp: initially b (can be changed only once) 
 - consensus problem: design a protocol so that at the end, either
  1.  all processes set their output variables to 0 (all-0's)
  2. or all processes set their output variables to 1 (all-1's)
  
// different way to put it
 - every process contributes a value
 - goal is to have all processes decide same (some) value   
  - decision once made, can't be changed
 - there might be other constraints
  - validity    = if everyone proposes same value, then that's what is decided
  - integrity   = decided value must have been proposed by some process
  - Non-triviality  = there is atleast one initial system state that leads to each of the all-0's or all-1's outcome 
  
Why is it imp?
 - related (equivalent to or harder than) to many distributed problems  
  - perfect failure detection
  - leader election (select on one leader, and every alive process knows about it)
  - agreement (harder than consensus)
 - so it is a very imp problem 
 



Two different models of distributed systems

 - Synchronous system model
 
  - each msg is recvd within bounded time
  - drift of each process' local clock has a known bound
  - each step in a process takes lowerbound (lb) < time < upper bound (ub)
   - eg a collection of processors connected by a  communication bus, eg a Cray supercomputer or a multicore machine
   
 - Asynchronous system model
 
  - No bounds on process execution
  - the drift rate of a clock is arbitrary
  - no bounds on msg tranmission delays
  eg: the internet is an asynchronous distributed system, so are ad-hoc and sensor n/ws
  - this is a more general (and thus challenging) model than the synchronous system model.
  - a protocol for an asynch system will also work for a synch system (but not vice versa)
 
 
So, consensus possible or not in distributed system models?
 - In the synch system model, SOLVABLE
 - In the asynch system model - IMPOSSIBLE
  - whatever protocol/algorithm you suggest, there is always a  worst-case possible execution (with failure and msg delays) that prevents the sytem from reaching consensus
  - powerful result - see the FLP proof
  // http://the-paper-trail.org/blog/a-brief-tour-of-flp-impossibility/
  - subsequently, safe or probabilistic solutions have become quite popular to consensus or related problems 
  
  
----------
Lecture 3.2: Consensus in Synchronous system
----------

System model:
 - Synch system: bounds on
  - msg delays
  - upper bound on clock drift rates
  - max time for each process step
 - eg multi-processor, common clock across processors
 - processes can fail by stopping (crash stop or crash failure)  
 

Consensus in Synchronous system
 - For a system with atmost f processes crashing
  - all processes are synchronized and operate in "rounds" of time
  - the algorithm proceeds in f+1 rounds (with timeout), using reliable communication to all members
  - values(i,r) - the set of proposed values known to pi at the beginning of round r
  
 - Initial values (i,0) = {}; values(i,1) = {vi} // Pi's own contribution
  for round = 1 to f+1 do
   multicast (Values(i,r) - Values(i, r-1)) // iterate through processes, send each a msg
   Values(i,r+1) <- Values(i,r)  // set same values for the next round
   for each Vj recvd
    Values(i,r+1) = Values(i,r+1) UNION Vj
   end
  end
  di = min(Values(i,f+1))  // decision variable = min value
 - non-faulty processes end up with the same identical value in the algorithm 
 

Why does the algorithm work?
 - After f+1 rounds, all non-faulty processes would have recvd the same set of values. Proof by contradiction.
 - Assume 2 non-faulty processes, say pi & pj, differ in their final set of values (ie after f+1 rounds) 
 - assume that pi possesses a value v that pj does not
  - pi must have recvd v in the very last round
   - else pi would have sent v to pj in the last round
  - so, in the last round, a 3rd process pk must have sent v to pi, but then crashed before sending it to pj
  - similarly a 4th process sending v in last-but-one round must have crashed; otherwise both pk & pj should have recvd v
  - proceeding in this way, we infer atleast 1 unique crash in each of the preceding rounds
  - this means a total of f+1 crashes, while we have assumed at most f crashes can occur => contradiction 
  
  
----------
Lecture 3.3: Paxos, simply (soln to consensus prob in asynch model)
----------

Consensus problem in asynch model
 - impossible to solve (FLP proof)  
 - key to the proof: it is impossible to distinguish a failed process from one that is just very very (very) slow. Hence the rest of the alive processes may stay ambivalent (irresolute) (forever) when it comes to deciding
 - But consensus imp since it maps to many imp distributed computing problems
 - Um, can't we just solve consensus? - YES !
 
Paxos algo 
 - most popular "consensus solving" algo
 - does not solve consensus prob // which would be impossible, we already proved that
 - but provides safety and eventual liveness
 - a lof of systems use it
  - zookeeper (yahoo!), google chubby, and many others
 - invented by Leslie Lamport
 - provides safety and eventual liveness
  - safety: consensus is not violated
  - eventual liveness : If things go well sometime in future (msgs, failure etc), there is a good chance consensus will be reached. But there is no guarantee
 - FLP result still applies: Paxos is not guaranteed to reach consensus (ever, or within any bounded time) 
 
Political science 101: PAXOS GROKED
 - Paxos has rounds, each round has a unique ballot id
 - rounds are asynch
  - time synch is not reqd
  - if u r in round j, and hear a msg from round j+1, abort everything and move over to round j+1
  - use timeouts, may be pessimistic
 - each round broken into phases (which are also asynch)  
  - phase 1: a leader is elected (election)
  - phase 2: leader proposes a value, processes ACK (bill)
  - phase 3: leader multicasts final value (law)
  
Phase 1 - Election
 - potential leader choses a unique ballot id, higher than seen anything so far
 - sends to all processes
 - processes wait, respond once to highest ballot id
  - if potential leader sees a higher ballot id, it can't be a leader
  - paxos tolerant to multiple leaders, but we'll only discuss 1 leader case
  - processes also log recvd ballot ID on disk
 - if a process has in previous round decided on a value v', it includes v' in its reponse
 - if majority (quorum) responds ok, then u r the leader
  - if no one has majority, start new round
 - if things go right, a round can not have 2 leaders 
 
Phase 2 - Proposal (Bill)
 - leader sends proposal value v to all
  - use v=v' if some processes already decided in a previous  round and sent you its decided value v'
 - recipients log on disk; responds OK  
 
Phase 3 - Decision (law)
 - If leader hears a majority of OKs, it lets everyone know of the decision
 - recipient recv decision and log it on disk
 
 
 
Which is the point of no return?
 - ie when is the consensus reached in the system?
 - if/when a majority of processes hear proposed value and accept it (ie are about to/have respond(ed) with an OK) 
 - processes may not know it yet, but a decision has been made for the group
  - even leader doesn't know it yet
 - what if leader fails after that?
  - keep having rounds untill some round completes 
  
  
Safety
 - if some round has majority (quorum) hearing proposed value v' and accepting it (middle of phase 2), then subsequently at each round either:
  (1) the round chooses v' as decision
  (2) the round fails  
 - Proof: 
  - potential leader waits for majority of OKs in phase 1
  - atleast 1 will contain  v' (because two majorities or quorums always intersect) 
  - it will choose to send out v' in phase 2
 - success requires a majority and any two majority sets intersect 
 
What could go wrong?
 - process fails
  - majority doesn't include it
  - when process restarts, it uses log to retrieve a past decision (if any) and past seen ballot ids. Tries to know of past decisions
 - leader fails
  - start another round
 - msgs dropped
  - if too flaky, just start another round
 - note that anyone can start a round at anytime
 - protocol may never end - tough luck, buddy !
  - impossibility result not violated
  - if things go well sometime in future, consensus reached  
  
Summary
 - consensus is very imp problem
  - equivalent to many imp distributed computing problems that have to do with reliability
 - consensus is possible to solve in synch system where msg delays and processing delays are bounded
 - consensus is impossible to solve in an asynch system where delays are unbounded
 - paxos protocol - widely used implementation of a safe, eventually-live  consensus protocol for asynch systems
  - Paxos (or variants) used in Apache Zookeeper, Google's Chubby system, Active disk paxos, and many other cloud computing systems
