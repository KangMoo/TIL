

## Keystone



> ```shell
> $ openstack user list
> $ openstack role list
> $ openstack project list
> $ openstack role list --user admin --project admin
> $ openstack service list
> ```



**Demo 사용자 생성**

> ```shell
> $ openstack project create --description "Demo Project" demo
> $ openstack user create --password abc123 –project demo demo
> $ openstack role add --project demo --user demo _member_
> $ openstack role list --project demo --user demo 
> ```



## Glnace

**CLI로 구성하는 glance**

>```shell
>$ glance image-list
>$ glance image-create
>$ glance image-show small or id
>```



## NOVA

> ```shell
> nova keypair-add key1 > /root/key2.pem
> nova keypair-show key1
> chmod 600 /root/key1.pem
> nova floating-ip-list
> nova flavor-list
> nova image-list
> nova list
> nova secgroup-create
> nova secgroup-add-rule
> openstack-status
> nova boot
> nova delete
> nova volume-attach cm _uuid auto
> ```
>
> 