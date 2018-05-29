import unittest
import argparse
import ConfigParser
import os
from status_job_submit import compose_command
from utils.common import cmd_toString

CONF_TEMPLATE = os.path.join(os.path.dirname(__file__), '../conf/conf.template')


class TestClass(unittest.TestCase):

    def test_compose_command(self):

        # set up the config parser
        config = ConfigParser.ConfigParser()
        config.read(CONF_TEMPLATE)

        parser = argparse.ArgumentParser()
        parser.add_argument('--Tenant')
        parser.add_argument('--Date')
        parser.add_argument('--Report')
        parser.add_argument('--Sudo', action='store_true')
        parser.add_argument('--Method')
        args = parser.parse_args(['--Tenant', 'TENANTA', '--Date', '2018-02-11', '--Report', 'Critical', '--Method', 'upsert'])

        hdfs_metric = "hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/mdata"
        hdfs_sync = "hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/sync"

        test_hdfs_commands = {}

        test_hdfs_commands["--pdata"] = hdfs_metric+"/2018-02-10"
        test_hdfs_commands["--mdata"] = hdfs_metric+"/2018-02-11"
        test_hdfs_commands["--conf"] = hdfs_sync+"/TENANTA_Critical_cfg.json"
        test_hdfs_commands["--mps"] = hdfs_sync+"/Critical/"+"metric_profile_2018-02-11.avro"
        test_hdfs_commands["--ops"] = hdfs_sync+"/TENANTA_ops.json"
        test_hdfs_commands["--apr"] = hdfs_sync+"/TENANTA_Critical_ap.json"
        test_hdfs_commands["--egp"] = hdfs_sync+"/Critical/group_endpoints_2018-02-11.avro"
        test_hdfs_commands["--ggp"] = hdfs_sync+"/Critical/group_groups_2018-02-11.avro"

        test_cmd = "flink_path run -c test_class test.jar --run.date 2018-02-11 --mongo.uri mongodb://mongo_test_host:mongo_test_port/argo_TENANTA --mongo.method upsert --mdata hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/mdata/2018-02-11 --mps hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/sync/Critical/metric_profile_2018-02-11.avro --apr hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/sync/TENANTA_Critical_ap.json --ggp hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/sync/Critical/group_groups_2018-02-11.avro --conf hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/sync/TENANTA_Critical_cfg.json --egp hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/sync/Critical/group_endpoints_2018-02-11.avro --pdata hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/mdata/2018-02-10 --ops hdfs://hdfs_test_host:hdfs_test_port/user/hdfs_test_user/argo/tenants/TENANTA/sync/TENANTA_ops.json --ams.proxy test_proxy --ams.verify true"

        self.assertEquals(test_cmd, cmd_toString(compose_command(config, args, test_hdfs_commands)))