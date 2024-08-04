<h1 align="center">
RoxyMC Website
</h1>

## Compiling

### Frontend
You can use gradle `generate` npm task:
```shell
./gradlew generate
```
or directly run:
```shell
cd frontend
npm run generate
```

### Backend
Simply run:
```shell
./gradlew shadowJar
```

## Dev Running

### Frontend
Running frontend with auto-reload is simple, however, if you didn't already do this, you need to install all dependencies with `postInstall` task:
```shell
./gradlew postInstall
```
and then run:
```shell
cd frontend
npm run dev
```
> [!NOTE]
> Any backend api calls won't work unless you run the backend.

### Backend
First, you need to execute `run` task:
```shell
./gradlew run
```
and then launch auto-reload in a separate terminal:
```shell
./gradlew -t jar
```