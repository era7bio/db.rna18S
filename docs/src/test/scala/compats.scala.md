
```scala
package era7bio.db

import ohnosequences.statika._, aws._
import ohnosequences.awstools._, regions.Region._, ec2._, InstanceType._, autoscaling._, s3._

case object rna18sCompats {

  case object generateRna18sDB extends Compatible(
    amznAMIEnv(
      AmazonLinuxAMI(Ireland, HVM, InstanceStore),
      javaHeap = 20 // in G
    ),
    rna18s.generate,
    generated.metadata.DbRna18S
  )
}

```




[test/scala/Dbrna18s.scala]: Dbrna18s.scala.md
[test/scala/runBundles.scala]: runBundles.scala.md
[test/scala/compats.scala]: compats.scala.md
[main/scala/rna18s.scala]: ../../main/scala/rna18s.scala.md