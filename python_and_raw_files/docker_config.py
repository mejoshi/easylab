import os
import time
import extract_info

def start():
    container_start_status = os.system("docker run -dit --name easylab1 easylab:v1.0");
    if container_start_status == 0:
        time.sleep(2)
        tunneling_start_status = os.system("docker cp easylab1:/ngrok.log .")
        if tunneling_start_status == 0:
            print("Working great!!")
            url = extract_info.extraction()
            return url
        else:
            return "something went wrong with starting tunneling......"
    else:
        return "Something went wrong with creating container"


def stop():
    container_stop_status = os.system("docker stop easylab1")
    container_remove_status = os.system("docker rm easylab1")
    if container_stop_status == 0:
        if container_remove_status==0:
            return "container stopped and removed successfully!"
        else:
            return "Container has stopped, but not removed due to an error"
    else:
        if container_remove_status==0:
            return "container has been removed!!"
