# Gilded Rose Kata

[Gilded Rose Kata](https://github.com/circleci/gilded-rose-kata) is a kata that is solved with JavaScript in this repository.

## Running

To run the environment is necessary `Docker`.

In `elm` directory run:

```sh
docker build -t gilded_rose_kata/elm .
docker run -ti --rm -v $(pwd):/code --workdir /code gilded_rose_kata/elm bash
```

You can run the test suite from the exercise
directory with:

```sh
elm-test
```    
