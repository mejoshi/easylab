# Using flask to make an api
# import necessary libraries and functions
from flask import Flask, jsonify, request
import docker_config
import extract_info
app = Flask(__name__)


@app.route('/start', methods=['POST'])
def startContainer():
    if request.method == 'POST':
        data = docker_config.start()
        data = {"url":data,"username":"easylab","password":"easylab@123"}
        return jsonify({'start_details': data})


@app.route('/stop', methods=['POST'])
def stopContainer():
    if request.method == 'POST':
        data = docker_config.stop()
        return jsonify({'stop_details': data})


if __name__ == '__main__':
    app.run(debug=True)
