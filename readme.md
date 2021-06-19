List of available requests

## Add a new task

This API adds a new task to the table using POST method

### curl request

```curl
curl --location --request POST 'http://127.0.0.1:8080/task' \
--header 'Content-Type: application/json' \
--data-raw '{
    "task": "Buy groceries"
}'
```

#### Response

```json
{
    "id": 5,
    "task": "Buy groceries",
    "completed": false,
    "createdAt": "2021-06-08T14:56:53.955+00:00",
    "completedAt": null
}
```

## Mark a task as completed

This PUT request will make the following two changes to the specified task.

* Changed the completed flag to `True` from `False`
* Updated the `completedAt` field with the current date time

### Request
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

## Delete a task

This API deletes a task from the tasks table using the `DELETE` method. 
The task id will be passed in the URL as a path parameter.

This API returns the following two responses

* Success Response (If the task is deleted successfully)
* Failure Response (If the task did not exist)

The response body is constructed using `HashMap`. The response
is returned and different status code for each response is set using `ResponseEntity`. 
### Request

```curl
curl --location --request DELETE 'http://127.0.0.1:8080/task/5'
```

#### Success Response
```json
{
    "message": "Task deleted successfully!",
    "status": "Success"
}
```

#### Failure Response
```json
{
    "code": 1001,
    "Message": "Task not found",
    "status": "Failure"
}
```

### Fetch all tasks

This API returns all the available tasks.
Following different filters are supported by this API.

* search by name (caseless partial-match)
* filter by from date
* filter by to date
* filter by task status (completed/not completed)

Pagination is also supported. By default, page 1 and page size 10 will be used.

I have used `criteria` to implement dynamic filtering. The tasks queryset will be passed to the filter, Where
it will be filtered based on the value provided. If no value is given (null), it will be ignored.

Custom Response format is used.

### Request to fetch all tasks without any filters

```curl
curl --location --request GET 'http://127.0.0.1:8080/task' \
--header 'Content-Type: application/json'
```

#### Response

```json
{
    "total": 4,
    "data": [
        {
            "id": 6,
            "task": "Buy groceries",
            "completed": false,
            "createdAt": "2021-06-08T14:57:31.000+00:00",
            "completedAt": null
        },
        {
            "id": 3,
            "task": "Get your life together!",
            "completed": false,
            "createdAt": "2021-06-01T05:51:11.000+00:00",
            "completedAt": null
        },
        {
            "id": 2,
            "task": "Visit Grandma",
            "completed": false,
            "createdAt": "2021-05-20T09:34:32.000+00:00",
            "completedAt": null
        },
        {
            "id": 1,
            "task": "Visit Paris",
            "completed": true,
            "createdAt": "2021-05-20T09:34:23.000+00:00",
            "completedAt": "2021-05-29T18:54:06.000+00:00"
        }
    ],
    "page": 1,
    "size": 10
}
```

### Request to filter only completed tasks

```curl
curl --location --request GET 'http://127.0.0.1:8080/task?completed=true' \
--header 'Content-Type: application/json'
```

#### Response

```json
{
    "total": 1,
    "data": [
        {
            "id": 1,
            "task": "Visit Paris",
            "completed": true,
            "createdAt": "2021-05-20T09:34:23.000+00:00",
            "completedAt": "2021-05-29T18:54:06.000+00:00"
        }
    ],
    "page": 1,
    "size": 10
}
```

### Request to filter non-completed tasks

```curl
curl --location --request GET 'http://127.0.0.1:8080/task?completed=false' \
--header 'Content-Type: application/json'
```

#### Response

```json
{
    "total": 3,
    "data": [
        {
            "id": 6,
            "task": "Buy groceries",
            "completed": false,
            "createdAt": "2021-06-08T14:57:31.000+00:00",
            "completedAt": null
        },
        {
            "id": 3,
            "task": "Get your life together!",
            "completed": false,
            "createdAt": "2021-06-01T05:51:11.000+00:00",
            "completedAt": null
        },
        {
            "id": 2,
            "task": "Visit Grandma",
            "completed": false,
            "createdAt": "2021-05-20T09:34:32.000+00:00",
            "completedAt": null
        }
    ],
    "page": 1,
    "size": 10
}
```

### Request to filter tasks created after a given date

```curl
curl --location --request GET 'http://127.0.0.1:8080/task?from=01-06-2021' \
--header 'Content-Type: application/json'
```

#### Response
```json
{
    "total": 2,
    "data": [
        {
            "id": 6,
            "task": "Buy groceries",
            "completed": false,
            "createdAt": "2021-06-08T14:57:31.000+00:00",
            "completedAt": null
        },
        {
            "id": 3,
            "task": "Get your life together!",
            "completed": false,
            "createdAt": "2021-06-01T05:51:11.000+00:00",
            "completedAt": null
        }
    ],
    "page": 1,
    "size": 10
}
```

### Request to filter tasks created until a given date

```curl
curl --location --request GET '127.0.0.1:8080/task?to=01-06-2021'
```

#### Response

```json
{
    "total": 2,
    "data": [
        {
            "id": 2,
            "task": "Visit Grandma",
            "completed": false,
            "createdAt": "2021-05-20T09:34:32.000+00:00",
            "completedAt": null
        },
        {
            "id": 1,
            "task": "Visit Paris",
            "completed": true,
            "createdAt": "2021-05-20T09:34:23.000+00:00",
            "completedAt": "2021-05-29T18:54:06.000+00:00"
        }
    ],
    "page": 1,
    "size": 10
}
```

### Return only task names as a list

```curl
curl --location --request GET '127.0.0.1:8080/task-names'
```

#### Response

```json
[
  "Buy groceries",
  "Get your life together!",
  "Visit Grandma",
  "Visit Paris"
]
```

### Return task id and names alone

This API used `projections` to return only the required fields, instead of returning all the fields.
This API also has a custom field.

```curl
curl --location --request GET '127.0.0.1:8080/task-id-name
```

#### Response

```json
[
    {
        "task": "Visit Paris",
        "message": "This is a custom message",
        "id": 1
    },
    {
        "task": "Visit Grandma",
        "message": "This is a custom message",
        "id": 2
    },
    {
        "task": "Finish your Homework!",
        "message": "This is a custom message",
        "id": 3
    },
    {
        "task": "Buy groceries",
        "message": "This is a custom message",
        "id": 6
    }
]
```

### Fetch task details from ID

```curl
curl --location --request GET '127.0.0.1:8080/task/1'
```

#### Response

```json
{
    "id": 1,
    "task": "Visit Paris",
    "completed": true,
    "createdAt": "2021-05-20T09:34:23.000+00:00",
    "completedAt": "2021-05-29T18:54:06.000+00:00"
}
```

## Topics covered

* REST API (GET, POST, PUT, DELETE)
* Criteria API (Dynamic query filtering)
* Pagination
* Interface
* Response Entity
* Custom response





