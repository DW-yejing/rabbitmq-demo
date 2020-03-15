# encoding: utf-8

import pika

def app():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    # channel.queue_declare(queue='srmis.queue')
    channel.basic_consume(queue='srmis.queue',
                      auto_ack=True,
                      on_message_callback=callback)
    channel.start_consuming()

def callback(ch, method, properties, body):
    print(" [x] Received %r" % body)



if __name__=="__main__":
    app()