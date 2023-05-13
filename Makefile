# Define variables
COMPOSE = docker-compose
DOCKER = docker
APP_NAME = my_app

# Define targets
.PHONY: help
help:
	@echo "Usage: make <target>"
	@echo "Targets:"
	@echo "  build             Build Docker images"
	@echo "  up                Start the application"
	@echo "  down              Stop the application and remove containers"
	@echo "  clean             Remove all containers, images, and volumes"

.PHONY: build
build:
	$(COMPOSE) build

.PHONY: up
up:
	$(COMPOSE) up -d

.PHONY: down
down:
	$(COMPOSE) down

.PHONY: clean
clean:
	$(COMPOSE) down -v --rmi all --remove-orphans
	$(DOCKER) system prune -af
