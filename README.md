# Lamport Clock
The Lamport clock is a logical clock created by Lamport Leslie in 1978 that allows to order events in communication between nodes. Being a logical clock overcomes the problems of physical clocks, these are due to the fact that physical clocks are unpersistent compared to the concept of randomness, mind logical clocks no. <br>

Lamport Clock is made of an interger held locally by the nodes in the system. When an event occurred the node must increment of a value the logical clock. By event we mean  messages sent or received and the execution of some code in the node. <br>

System Functionality: <br>
![image](https://github.com/user-attachments/assets/36ed30cf-b464-4145-b782-0d1fd98e10ad)

Node A send messages to node B and C, like the previous node, node B sent messages at two nodes: node A and C, whilst node C send messages only to node A. <br>

The messages sent are made of the sender's lamport clock and the actual message. <br>

Simple demostration of the algorithm involved: <br>
```
on node start:
 lamportClock := 0;
end

on receiving messages:
 lamportClock := max(lamportClock, senderLamportClock) + 1;
end

on sending messages:
 lamportClock := lamportClock + 1;
end
```



 
