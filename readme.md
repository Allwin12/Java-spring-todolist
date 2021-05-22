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