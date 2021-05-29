List of available requests

## Fetch all tasks

The results are paginated with default page size 5 and page number 0

### curl request

```curl
curl --location --request GET 'http://127.0.0.1:8080/task'
```

### Response

```json
{
    "content": [
        {
            "id": 1,
            "task": "Buy Groceries",
            "completed": false,
            "createdAt": "2021-05-20T09:34:23.000+00:00",
            "completedAt": null
        },
        {
            "id": 2,
            "task": "Finish the blog post",
            "completed": false,
            "createdAt": "2021-05-20T09:34:32.000+00:00",
            "completedAt": null
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 10,
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "numberOfElements": 2,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "size": 10,
    "number": 0,
    "first": true,
    "empty": false
}
```
## Mark a task as completed

This PUT request will make the following two changes to the specified task.

* Changed the completed flag to `True` from `False`
* Updated the `completedAt` field with the current date time

#### Request
```curl
curl --location --request PUT 'http://127.0.0.1:8080/task/1'
```

#### Response
```json
{
    "id": 1,
    "task": "Buy Groceries",
    "completed": true,
    "createdAt": "2021-05-20T09:34:23.000+00:00",
    "completedAt": "2021-05-29T18:54:06.135+00:00"
}
```

## Custom Responses

### Return response using a HashMap

This GET request will take a number in the path argument and return the list of 
even and odd numbers in that range.

#### Request

```curl
curl --location --request GET 'http://127.0.0.1:8080/numbers/10'
```

#### Response

```json
{
    "even": [
        2,
        4,
        6,
        8,
        10
    ],
    "odd": [
        1,
        3,
        5,
        7,
        9
    ]
}
```