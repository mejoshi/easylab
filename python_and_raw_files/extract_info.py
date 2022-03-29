def extraction():
    log = open("ngrok.log", "r")
    data = log.read()
    return data[666:]