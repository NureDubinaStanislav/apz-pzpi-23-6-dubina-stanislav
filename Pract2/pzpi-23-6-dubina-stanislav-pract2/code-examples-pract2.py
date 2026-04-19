import requests
from random import random, choice
from apscheduler.schedulers.background import BackgroundScheduler
from cassandra.cluster import Cluster

def select_server(user_location, content_id):
    nearby_servers = get_servers_by_region(
        user_location
    )
    available = [
        s for s in nearby_servers
        if has_content(s, content_id)
    ]
    return min(
        available,
        key=lambda s: s.latency + s.load * 0.3
    )

response = requests.post(
    'https://api.netflix.com/playback/v1/start',
    headers={'Authorization': f'Bearer {userToken}'},
    json={
        'contentId': 'tt1234567',
        'deviceType': 'WEB'
    }
)

manifestUrl, drmToken = (
    response.json()['manifestUrl'],
    response.json()['drmToken']
)

KILL_PROBABILITY = 0.1

scheduler = BackgroundScheduler()

@scheduler.scheduled_job("interval", hours=1)
def chaos_monkey():
    instances = discovery_client.get_instances()
    if random() < KILL_PROBABILITY:
        target = choice(instances)
        instance_manager.terminate(target.id)


cluster = Cluster(["db1"])
session = cluster.connect("app")
row = session.execute(
    "SELECT name FROM users WHERE id=%s",
    [user_id]
).one()
