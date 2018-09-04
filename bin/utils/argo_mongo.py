#!/usr/bin/env python
import sys
import logging
import argparse
import pymongo
from pymongo import MongoClient
from pymongo.errors import ServerSelectionTimeoutError
from argo_config import ArgoConfig
from common import get_config_paths
from common import get_log_conf

log = logging.getLogger(__name__)


class ArgoMongoClient(object):

    def __init__(self, args, config, cols):
        self.args = args
        self.config = config
        self.cols = cols

    def mongo_clean_ar(self, uri):

        tenant_report = None

        # if report is given check if report exists in configuration
        if self.args.report:
            report_name = self.args.report
            tenant_group = "TENANTS:" + self.args.tenant
            if report_name in self.config.get(tenant_group, "reports"):
                tenant_report = self.config.get(tenant_group, "report_"+report_name)
            else:
                log.critical("Report %s not found", report_name)
                sys.exit(1)

        # Create a date integer for use in the database queries
        date_int = int(self.args.date.replace("-", ""))

        # set up the mongo client
        try:
            log.info("Trying to connect to: " + uri.geturl())
            client = MongoClient(uri.geturl())
            # force a connection to test the client
            client.server_info()
        except ServerSelectionTimeoutError as pse:
            log.fatal(pse)
            sys.exit(1)

        # specify the db we will be using. e.g argo_TENANTA
        # from the uri, take the path, which reprents the db, and ignore the / in the begging
        db = client[uri.path[1:]]

        # iterate over the specified collections
        for col in self.cols:
            if tenant_report is not None:
                num_of_rows = db[col].find({"date": date_int, "report": tenant_report}).count()
                log.info("Collection: " + col + " -> Found " + str(
                    num_of_rows) + " entries for date: " + self.args.date + " and report: " + self.args.report)
            else:
                num_of_rows = db[col].find({"date": date_int}).count()
                log.info("Collection: " + col + " -> Found " + str(
                    num_of_rows) + " entries for date: " + self.args.date + ". No report specified!")

            if num_of_rows > 0:

                if tenant_report is not None:
                    # response returned from the delete operation
                    res = db[col].delete_many({"date": date_int, "report": tenant_report})
                    log.info("Collection: " + col + " -> Removed " + str(res.deleted_count) +
                             " entries for date: " + self.args.date + " and report: " + self.args.report)
                else:
                    # response returned from the delete operation
                    res = db[col].delete_many({"date": date_int, "report": tenant_report})
                    log.info("Collection: " + col + " -> Removed " + str(
                        res.deleted_count) + " entries for date: " + self.args.date + ". No report specified!")
                log.info("Entries removed successfully")
            else:
                log.info("Zero entries found. Nothing to remove.")

        # close the connection with mongo
        client.close()

    def mongo_clean_status(self, uri):

        tenant_report = None

        # if report is given check if report exists in configuration
        if self.args.report:
            report_name = self.args.report
            tenant_group = "TENANTS:" + self.args.tenant
            if report_name in self.config.get(tenant_group, "reports"):
                tenant_report = self.config.get(tenant_group, "report_"+report_name)
            else:
                log.critical("Report %s not found", report_name)
                sys.exit(1)

        # Create a date integer for use in the database queries
        date_int = int(self.args.date.replace("-", ""))

        # set up the mongo client
        try:
            log.info("Trying to connect to: " + uri.geturl())
            client = MongoClient(uri.geturl())
            # force a connection to test the client
            client.server_info()
        except pymongo.errors.ServerSelectionTimeoutError as pse:
            log.fatal(pse)
            sys.exit(1)

        # specify the db we will be using. e.g argo_TENANTA
        # from the uri, retrieve the path section, which reprents the db, and ignore the / in the begging
        db = client[uri.path[1:]]

        # iterate over the specified collections
        for col in self.cols:
            if tenant_report is not None:
                num_of_rows = db[col].find({"date_integer": date_int, "report": tenant_report}).count()
                log.info("Collection: " + col + " -> Found " + str(
                    num_of_rows) + " entries for date: " + self.args.date + " and report: " + self.args.report)
            else:
                num_of_rows = db[col].find({"date": date_int}).count()
                log.info("Collection: " + col + " -> Found " + str(
                    num_of_rows) + " entries for date: " + self.args.date + ". No report specified!")

            if num_of_rows > 0:

                if tenant_report is not None:
                    # response returned from the delete operation
                    res = db[col].delete_many({"date_integer": date_int, "report": tenant_report})
                    log.info("Collection: " + col + " -> Removed " + str(res.deleted_count) +
                             " entries for date: " + self.args.date + " and report: " + self.args.report)
                else:
                    # response returned from the delete operation
                    res = db[col].delete_many({"date_integer": date_int, "report": tenant_report})
                    log.info("Collection: " + col + " -> Removed " + str(
                        res.deleted_count) + " entries for date: " + self.args.Date + ". No report specified!")
                log.info("Entries removed successfully")
            else:
                log.info("Zero entries found. Nothing to remove.")

        # close the connection with mongo
        client.close()


def main_clean(args=None):
    # stand alone method to be used whenever we want to call the mongo_clean_status method independently

    # Get configuration paths
    conf_paths = get_config_paths(args.config)

    # Get logger config file
    get_log_conf(conf_paths['log'])

    # Get main configuration and schema
    config = ArgoConfig(conf_paths["main"], conf_paths["schema"])

    # set up the mongo uri
    tenant_group = "TENANTS:" + args.tenant
    mongo_uri = config.get(tenant_group, "mongo_uri")

    if args.job == "clean_ar":
        argo_mongo_client = ArgoMongoClient(args, config, ["service_ar", "endpoint_group_ar"])
        argo_mongo_client.mongo_clean_ar(mongo_uri)

    elif args.job == "clean_status":
        argo_mongo_client = ArgoMongoClient(args, config, ["status_metrics", "status_endpoints", "status_services",
                                                           "status_endpoint_groups"])
        argo_mongo_client.mongo_clean_status(mongo_uri)


# Provide the ability to the script, to run as a standalone module
if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Mongo clean up script")
    parser.add_argument(
        "-t", "--tenant", metavar="STRING", help="Name of the tenant", required=True, dest="tenant")
    parser.add_argument(
        "-r", "--report", metavar="STRING", help="Tenant report to be used", required=True, dest="report")
    parser.add_argument(
        "-d", "--date", metavar="STRING", help="Date to run the job for", required=True, dest="date")
    parser.add_argument(
        "-c", "--config", metavar="STRING", help="Path for the config file", dest="config")
    parser.add_argument(
        "-j", "--job", metavar="STRING", help="Stand alone method we wish to run", required=True, dest="job")

    # Parse the arguments
    sys.exit(main_clean(parser.parse_args()))
