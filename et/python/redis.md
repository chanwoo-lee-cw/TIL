## package를 이용한 redis

```
project
├── __init__.py 
├── config.py
├── redis_connection.py
├── redis_manager.py
```

```python
# redis_manager/__init__.py
from redis_manager.config import get_redis_config
from .redis_connection import RedisConnectionManager

_redis_connect = RedisConnectionManager.get_instance(get_redis_config())

# 재귀 오류가 뜨기 때문에 이 import는 밑에
from redis_manager.redis_manager import read, write, RedisHash

__all__ = ["read", "write", "RedisHash"]
```

```python
# redis_manager/config.py
import os

def get_redis_config():
    return {
        "host": os.getenv("REDIS_HOST", ""),
        "port": os.getenv("REDIS_PORT", ""),
    }
```

```python
# redis_manager/redis_connection.py
import redis

class RedisConnectionManager:
    _instance = None
    _connection = None

    @classmethod
    def get_instance(cls, config):
        if cls._instance is None:
            cls._instance = cls.__new__(cls)
            cls._connection = cls._initialize_connection(config)
        return cls._instance

    @staticmethod
    def _initialize_connection(config):
        return redis.Redis(host=config['host'], port=config['port'])

    @property
    def connection(self):
        return self._connection
```

```python
# redis_manager/redis_manager.py
from app import cache
from app.common.redis_manager import _redis_connect

redis_client = _redis_connect.connection

def write(key, value):
    redis_client.set(key, value)

def read(key):
    return redis_client.get(key)

class RedisHash:
    def __init__(self, hash_key):
        self.hash_key = hash_key

    def write(self, key, value):
        redis_client.hset(self.hash_key, key, value)

    def read(self, key):
        return redis_client.get(self.hash_key, key)
```