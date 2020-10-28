import subprocess

from flask import Flask, request, render_template

app = Flask("__name__")


@app.route("/add")
def addition():
    if 'a' in request.args:
        a = int(request.args.get('a'))
    else:
        a = 0
    if 'b' in request.args:
        b = int(request.args.get('b'))
    else:
        b = 0
    return str(a) + ' + ' + str(b) + ' == ' + str(a + b) + '<br>' + "thanks" + '<br>'


@app.route('/')
def hello_world():
    return render_template('index.html')


@app.route('/rail')
def rail_schedule():
    if 'outformat' in request.args:
        outformat = request.args.get('outformat')
    else:
        outformat = 'html'
        print("roni")

    if 'source' in request.args:
        source = request.args.get('source')
    else:
        source = 'def'
        print("roni")

    if 'destination' in request.args:
        destination = request.args.get('destination')
    else:
        destination = 'def'
        print("roni")

    if 'hour' in request.args:
        hour = request.args.get('hour')

    else:
        hour = '0'
        print("roni")

    if 'minutes' in request.args:
        minutes = request.args.get('minutes')
    else:
        minutes = '0'
        print("roni")

    return subprocess.check_output(
        ["java", "-classpath", "/home/ronikhizverg/git/TrainSchedule/TrainSchedule/bin", "userSystem/UserMain",
         outformat, source, destination, hour, minutes])

    # app.run(port=5000, host="0.0.0.0")
    # curl "http://localhost:5000/rail?source=Ofakim&destination=Tel%20Aviv-University&hour=5&minutes=20"
