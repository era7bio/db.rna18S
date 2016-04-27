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
