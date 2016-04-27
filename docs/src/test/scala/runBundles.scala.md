
```scala
package era7bio.db.test

import ohnosequences.statika._, aws._
import ohnosequences.awstools._, regions.Region._, ec2._, InstanceType._, autoscaling._, s3._

import era7bio.db._
import era7.defaults._


case object rna18sDBRelease {

  def launch(user: AWSUser): List[String] = {
    EC2.create(user.profile)
      .runInstances(
        amount = 1,
        rna18sCompats.generateRna18sDB.instanceSpecs(
          c3.x2large,
          user.keypair.name,
          Some(ec2Roles.projects.name)
        )
      )
      .map { _.getInstanceId }
  }

}

```




[test/scala/Dbrna18s.scala]: Dbrna18s.scala.md
[test/scala/runBundles.scala]: runBundles.scala.md
[test/scala/compats.scala]: compats.scala.md
[main/scala/rna18s.scala]: ../../main/scala/rna18s.scala.md