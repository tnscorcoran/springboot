# To run on Openshift(substitute as appropriate):
-----------------------------------------------
First Login and create a project
```
oc login <TOKEN>
oc new-project a-springboot
```
## To install from Github using S2I

```
oc new-app openshift/redhat-openjdk18-openshift:1.3~https://github.com/tnscorcoran/springboot.git
oc expose service springboot
```
Now, once the build and deployment are done, it’s available to test:

curl <route>/flights/intl/flights

in my case:

curl https://spingboot-flights-a-springboot.apps.cluster-twh5m.twh5m.sandbox1645.opentlc.com/flights/intl/flights



## To install using yaml (Deployment, Service, Route)
This deployment uses a container image pushed to Quay.io. See below, for instructions to push to your own Quay.

```
oc apply -f - <<EOF
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboot-flights
spec:
  replicas: 1
  selector:
    matchLabels:
      app: springboot-flights
  template:
    metadata:
      labels:
        app: springboot-flights
    spec:
      containers:
      - name: springboot-flights
        image: quay.io/tcorcoran/springboot-flights:latest
        ports:
        - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: springboot-flights
spec:
  ports:
    - name: 8080-tcp
      protocol: TCP
      port: 8080
      targetPort: 8080
  selector:
    app: springboot-flights
  type: ClusterIP
---
apiVersion: route.openshift.io/v1
kind: Route
metadata:
  name: springboot-flights
spec:
  to:
    kind: Service
    name: springboot-flights
  port:
    targetPort: 8080-tcp
  tls:
    termination: edge
    insecureEdgeTerminationPolicy: Redirect
EOF
```

## To build a container image and push to quay (or other image repo) - substitute with your repo

Clone this repo and change directory to it.

```
podman login quay.io/tcorcoran
podman build -t quay.io/tcorcoran/springboot-flights .
podman push quay.io/tcorcoran/springboot-flights
```

