import subprocess

from flask import Flask, request, render_template

app = Flask("__name__")


@app.route('/')
def rail_schedule():
    if 'outformat' in request.args:
        outformat = request.args.get('outformat')
    else:
        outformat = 'html'

    if 'source' in request.args:
        source = request.args.get('source')
    else:
        source = 'Modiin'

    if 'destination' in request.args:
        destination = request.args.get('destination')
    else:
        destination = 'Lod'

    if 'hour' in request.args:
        hour = request.args.get('hour')

    else:
        hour = '5'

    if 'minutes' in request.args:
        minutes = request.args.get('minutes')
    else:
        minutes = '30'

    return subprocess.check_output(
        ["java", "-classpath", "/home/ronikhizverg/git/TrainSchedule/TrainSchedule/bin", "userSystem/UserMain",
         outformat, source, destination, hour, minutes])

    # app.run(port=5000, host="0.0.0.0")
    # curl "http://localhost:5000/rail?source=Ofakim&destination=Tel%20Aviv-University&hour=5&minutes=20"
