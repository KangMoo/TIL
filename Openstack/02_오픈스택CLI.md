

## Keystone

* 

  > ```shell
  > $ openstack user list
  > $ openstack role list
  > $ openstack project list
  > $ openstack role list --user admin --project admin
  > $ openstack service list
  > ```

  

* Demo 사용자 생성

  > ```shell
  > $ openstack project create --description "Demo Project" demo
  > $ openstack user create --password abc123 –project demo demo
  > $ openstack role add --project demo --user demo _member_
  > $ openstack role list --project demo --user demo 
  > ```

  

## Glnace

* 