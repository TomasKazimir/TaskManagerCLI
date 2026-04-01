# Task Manager CLI

Tiny Java command-line app for tracking tasks in memory.

Commands: `add <description>`, `list`, `complete <task_id>`, `delete <task_id>`, `exit`.


#### Disclaimer

Developed as a training exercise, this app is not intended for production use.
It lacks features like data persistence, user authentication, testing. Use at your own risk :D.

## Docker

### Build the Docker image
```
docker build -t taskmanager-main.cli .
```

### Run the CLI app in a container
```
docker run -it --rm taskmanager-main.cli
```

You can pass CLI arguments as needed:
```
docker run -it --rm taskmanager-main.cli <command> [args]
```

### Notes
- The Dockerfile uses multi-stage builds for a smaller final image.
- Source files are compiled to the `build/` directory.
- The entrypoint is set to `main.TaskManagerApp`.
- `.dockerignore` excludes unnecessary files from the build context.
