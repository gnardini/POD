CLASSPATH="."
	for dep in `ls server/target/pod-server-1.0-SNAPSHOT/lib/jars/*.jar`
do
	CLASSPATH="$CLASSPATH:$dep"
done

export CLASSPATH

rmid -J-Dsun.rmi.activation.execPolicy=none $* 
