# create image
docker build -t pawtropolis_db .

# create docker container postgres with a volume on our host
docker run --name pawtropolis_DB -p 5432:5432 -v C:\Users\vliguori\Desktop\Pawtropolis\app\src\main\resources\pgdata:/var/lib/postgresql/data -d pawtropolis_db

# create docker container postgres with a volume handled by Docker
docker run --name pawtropolis_DB -p 5432:5432 -v pawtropolis_data:/var/lib/postgresql/data -d pawtropolis_db

# create docker container pgadmin4
docker run --name pgadmin4 -e PGADMIN_DEFAULT_EMAIL=enzo@domain.com -e PGADMIN_DEFAULT_PASSWORD=admin -p 5050:80 -d dpage/pgadmin4 -e PGADMIN_DEFAULT_SERVER=pawtropolis_DB