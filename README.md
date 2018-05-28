To run on Openshift(substitute as appropriate):
-----------------------------------------------

oc login -u developer -p developer --insecure-skip-tls-verify=true 

oc new-project springboot

oc new-app openshift/redhat-openjdk18-openshift:1.3~https://github.com/tnscorcoran/springboot.git

oc expose service springboot

Now, once the build and deployment are done, it’s available to test:

curl route/flights/intl/flights

in my case:

curl http://springboot-springboot.apps.1.1.1.1.nip.io/flights/intl/flights
