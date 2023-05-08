
>[!SETUP]
>
> 1. **Create docker image**
>
>   *docker build -t pawtropolis_db .*
> 2. **Choose an option:**
>   1. **Create docker container postgres with a volume on our host**
>   
>      *docker run --name pawtropolis_DB -p 5432:5432 -v C:\Users\vliguori\Desktop\Pawtropolis\app\src\main\resources\pgdata:/var/lib/postgresql/data -d pawtropolis_db*
>   2. **Create docker container postgres with a volume handled by Docker**
>   
>       *docker run --name pawtropolis_DB -p 5432:5432 -v pawtropolis_data:/var/lib/postgresql/data -d pawtropolis_db*
> 3. [!Optional] **Create docker container pgadmin4 to have a graphical interface for our postgres db**
> 
>   *docker run --name pgadmin4 -e PGADMIN_DEFAULT_EMAIL=enzo@domain.com -e PGADMIN_DEFAULT_PASSWORD=admin -p 5050:80 -d dpage/pgadmin4 -e PGADMIN_DEFAULT_SERVER=pawtropolis_DB*