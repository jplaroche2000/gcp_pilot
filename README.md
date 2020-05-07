
Quick setup:
------------

Objective:

Replicating Oracle database tables to a GCP Firestore datastore and GCP memorystore (Redis) with Striim and Kafka/Confluent.  In addition we will use a hybrid cloud topology to bridge a local network to a GCP host project with service projects using VPN (OpenVPN).

--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--

Prerequisites:
- a running local Docker Desktop environment
- a running remote Kafka environment ([setup instructions for GCP - Debian OS](https://github.com/jplaroche2000/striim/blob/master/kafka/Build%20a%20Kafka%20Cluster%20on%20GCP.pdf))
- a GCP Firestore database in [Datastore mode](https://cloud.google.com/datastore/docs/quickstart)
- a GCP service account to access your GCP Firestore datastore.  To create one follow the steps descibed [here](https://cloud.google.com/iam/docs/creating-managing-service-account-keys#iam-service-account-keys-create-console), and copy the service account json file under striim/docker/java/ of the cloned git project.
- a GCP Memorystore database (Redis)

--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--/--

1. On your Docker machine:

    ```sh
    git clone https://github.com/jplaroche2000/gcp_pilot.git
    ```

    ```sh
    cd striim/docker
    ```

    a. Edit docker-compose.yml/striim/extra_hosts sections to reflect the public IP of your Kafka broker(s).

    >extra_hosts:
    
    >`-` "zoo1:10.255.0.6" 
    
    >`-` "zoo2:10.255.0.7"  

    >`-` "zoo3:10.255.0.8"  

    **THIS ASSUMES YOU HAVE KAFKA BROKER HOSTS NAMED zoo1, zoo2 and zoo3 advertising on port 9092**
 
    b. Edit docker-compose.yml/redis-commander/environment section to reflect the IP of the Redis instance
    
    >environment:
    
    >REDIS_HOSTS=10.255.1.3


2. Build the custom images

    ```sh
    docker-compose build
    ```
    If the Striim image build fails, see troobleshooting section below

3. Start the Docker stack

    ```sh
    docker-compose up -d
    ```


4. Access striim and add application

    http://localhost
    
    admin/admin
   
    In Striim web console:
    
    APPS > + AddApp > Import Existing App
   
    
    Load tql fle located at root of git project:
    
    striim/Test-MultiFeeds.tql
   
    Deploy App
   
    Start App
   
5. Access database and modify records to see changes replicated to Kafka topics
 
    ```sh
    sqlplus scott/tiger@//localhost:1521/XE
    ```
    
6. Access Redis commander console
 
    http://localhost:8081
   
7. To stop the Docker stack

    ```sh
    docker-compose down
    ```  


