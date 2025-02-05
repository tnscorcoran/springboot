# To Apply Red Hat Connectivity Link Controls to this API
-----------------------------------------------
## Prerequisites
- First Setup Springboot Flights API app as described in [Readme1-md](https://github.com/tnscorcoran/springboot/blob/master/README-1.md)
- Then follow these, which set up the compoentary required before you add your API as developer:
  - [Connectivity Link Setup Prerequisites](https://redhatquickcourses.github.io/rhcl-deploy/rhcl-deploy/1/chapter2/index.html)
  - [Connectivity Link Platform Engineer Workflow](https://redhatquickcourses.github.io/rhcl-deploy/rhcl-deploy/1/chapter3/index.html)

## Developer Workflow

### Deploy Flights API
Login to OpenShift with your token (got from __Copy Login Command__)
```
oc login <TOKEN>
```
Run the following

```
oc new-project a-springboot

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
EOF
```

### Create an HTTPRoute for your Gateway

Create an HTTPRoute for your previously created Gateway by running this command (correct your hostname):

```
oc apply -f -<<EOF
apiVersion: gateway.networking.k8s.io/v1
kind: HTTPRoute
metadata:
 name: springboot-http-route
 namespace: a-springboot
spec:
 hostnames:
   - springboot.managed.sandbox1237.opentlc.com
 parentRefs:
   - group: gateway.networking.k8s.io
     kind: Gateway
     name: external
     namespace: api-gateway
 rules:
   - backendRefs:
       - group: ''
         kind: Service
         name: springboot-flights
         port: 80
         weight: 1
     matches:
       - method: GET
         path:
           type: PathPrefix
           value: /flights/intl/flights
EOF
```


