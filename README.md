
**Building**

    cd $basedir
    mvn package docker:build

**Running**

    docker run load_tests

**Pass parameter for tests**
    
    <parameter name="testDuration" value="10000" /> in testng.xml




