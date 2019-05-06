#!/bin/bash
#ARG_POSITIONAL_SINGLE([testcase_home],[The directory of testcase])
#ARG_POSITIONAL_SINGLE([target_dir],[The target director that the test report put on])
#ARG_OPTIONAL_SINGLE([agent_repo],[],[The agent repository URL],[https://github.com/apache/incubator-skywalking.git])
#ARG_OPTIONAL_SINGLE([agent_branch],[],[The branch name of agent repository],[master])
#ARG_OPTIONAL_SINGLE([testcase_repo],[],[The test case repository URL],[https://github.com/SkyAPMTest/agent-auto-integration-testcases.git])
#ARG_OPTIONAL_SINGLE([testcase_branch],[],[The branch name of test case repository],[master])
#ARG_OPTIONAL_SINGLE([testcase_commitid],[ ],[The commit id of testcase repository],[])
#ARG_OPTIONAL_SINGLE([agent_commitid],[],[The commit id of agent repository],[])
#ARG_OPTIONAL_SINGLE([overwrite_readme],[],[Overwrite the README file],[off])
#ARG_OPTIONAL_SINGLE([log_dir],[],[The directory of validate log put in],[workspace/logs])
#ARG_OPTIONAL_SINGLE([validate_tool_jar_home],[],[The directory that validate tool jar put on, This directory relative to current script directory.],[workspace])
#ARG_OPTIONAL_SINGLE([upload_report],[ ],[Upload the test report to Github],[off])
#ARG_OPTIONAL_SINGLE([issue_no],[],[The issue no that this report relative to],[UNKNOWN])
#ARG_OPTIONAL_SINGLE([validate_log_url_prefix],[],[The url prefix of validate log url])
#DEFINE_SCRIPT_DIR([BUILD_HOME])
#ARG_HELP([])
#ARGBASH_GO()
# needed because of Argbash --> m4_ignore([
### START OF CODE GENERATED BY Argbash v2.7.1 one line above ###
# Argbash is a bash code generator used to get arguments parsing right.
# Argbash is FREE SOFTWARE, see https://argbash.io for more info


die()
{
	local _ret=$2
	test -n "$_ret" || _ret=1
	test "$_PRINT_HELP" = yes && print_help >&2
	echo "$1" >&2
	exit ${_ret}
}


begins_with_short_option()
{
	local first_option all_short_options='  h'
	first_option="${1:0:1}"
	test "$all_short_options" = "${all_short_options/$first_option/}" && return 1 || return 0
}

# THE DEFAULTS INITIALIZATION - POSITIONALS
_positionals=()
# THE DEFAULTS INITIALIZATION - OPTIONALS
_arg_agent_repo="https://github.com/apache/incubator-skywalking.git"
_arg_agent_branch="master"
_arg_testcase_repo="https://github.com/SkyAPMTest/agent-auto-integration-testcases.git"
_arg_testcase_branch="master"
_arg_testcase_commitid=
_arg_agent_commitid=
_arg_overwrite_readme="off"
_arg_log_dir="workspace/logs"
_arg_validate_tool_jar_home="workspace"
_arg_upload_report="off"
_arg_issue_no="UNKNOWN"
_arg_validate_log_url_prefix=


print_help()
{
	printf 'Usage: %s [--agent_repo <arg>] [--agent_branch <arg>] [--testcase_repo <arg>] [--testcase_branch <arg>] [--testcase_commitid <arg>] [--agent_commitid <arg>] [--overwrite_readme <arg>] [--log_dir <arg>] [--validate_tool_jar_home <arg>] [--upload_report <arg>] [--issue_no <arg>] [--validate_log_url_prefix <arg>] [-h|--help] <testcase_home> <target_dir>\n' "$0"
	printf '\t%s\n' "<testcase_home>: The directory of testcase"
	printf '\t%s\n' "<target_dir>: The target director that the test report put on"
	printf '\t%s\n' "--agent_repo: The agent repository URL (default: 'https://github.com/apache/incubator-skywalking.git')"
	printf '\t%s\n' "--agent_branch: The branch name of agent repository (default: 'master')"
	printf '\t%s\n' "--testcase_repo: The test case repository URL (default: 'https://github.com/SkyAPMTest/agent-auto-integration-testcases.git')"
	printf '\t%s\n' "--testcase_branch: The branch name of test case repository (default: 'master')"
	printf '\t%s\n' "--testcase_commitid: The commit id of testcase repository (no default)"
	printf '\t%s\n' "--agent_commitid: The commit id of agent repository (no default)"
	printf '\t%s\n' "--overwrite_readme: Overwrite the README file (default: 'off')"
	printf '\t%s\n' "--log_dir: The directory of validate log put in (default: 'workspace/logs')"
	printf '\t%s\n' "--validate_tool_jar_home: The directory that validate tool jar put on, This directory relative to current script directory. (default: 'workspace')"
	printf '\t%s\n' "--upload_report: Upload the test report to Github (default: 'off')"
	printf '\t%s\n' "--issue_no: The issue no that this report relative to (default: 'UNKNOWN')"
	printf '\t%s\n' "--validate_log_url_prefix: The url prefix of validate log url (no default)"
	printf '\t%s\n' "-h, --help: Prints help"
}


parse_commandline()
{
	_positionals_count=0
	while test $# -gt 0
	do
		_key="$1"
		case "$_key" in
			--agent_repo)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_agent_repo="$2"
				shift
				;;
			--agent_repo=*)
				_arg_agent_repo="${_key##--agent_repo=}"
				;;
			--agent_branch)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_agent_branch="$2"
				shift
				;;
			--agent_branch=*)
				_arg_agent_branch="${_key##--agent_branch=}"
				;;
			--testcase_repo)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_testcase_repo="$2"
				shift
				;;
			--testcase_repo=*)
				_arg_testcase_repo="${_key##--testcase_repo=}"
				;;
			--testcase_branch)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_testcase_branch="$2"
				shift
				;;
			--testcase_branch=*)
				_arg_testcase_branch="${_key##--testcase_branch=}"
				;;
			--testcase_commitid)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_testcase_commitid="$2"
				shift
				;;
			--testcase_commitid=*)
				_arg_testcase_commitid="${_key##--testcase_commitid=}"
				;;
			--agent_commitid)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_agent_commitid="$2"
				shift
				;;
			--agent_commitid=*)
				_arg_agent_commitid="${_key##--agent_commitid=}"
				;;
			--overwrite_readme)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_overwrite_readme="$2"
				shift
				;;
			--overwrite_readme=*)
				_arg_overwrite_readme="${_key##--overwrite_readme=}"
				;;
			--log_dir)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_log_dir="$2"
				shift
				;;
			--log_dir=*)
				_arg_log_dir="${_key##--log_dir=}"
				;;
			--validate_tool_jar_home)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_validate_tool_jar_home="$2"
				shift
				;;
			--validate_tool_jar_home=*)
				_arg_validate_tool_jar_home="${_key##--validate_tool_jar_home=}"
				;;
			--upload_report)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_upload_report="$2"
				shift
				;;
			--upload_report=*)
				_arg_upload_report="${_key##--upload_report=}"
				;;
			--issue_no)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_issue_no="$2"
				shift
				;;
			--issue_no=*)
				_arg_issue_no="${_key##--issue_no=}"
				;;
			--validate_log_url_prefix)
				test $# -lt 2 && die "Missing value for the optional argument '$_key'." 1
				_arg_validate_log_url_prefix="$2"
				shift
				;;
			--validate_log_url_prefix=*)
				_arg_validate_log_url_prefix="${_key##--validate_log_url_prefix=}"
				;;
			-h|--help)
				print_help
				exit 0
				;;
			-h*)
				print_help
				exit 0
				;;
			*)
				_last_positional="$1"
				_positionals+=("$_last_positional")
				_positionals_count=$((_positionals_count + 1))
				;;
		esac
		shift
	done
}


handle_passed_args_count()
{
	local _required_args_string="'testcase_home' and 'target_dir'"
	test "${_positionals_count}" -ge 2 || _PRINT_HELP=yes die "FATAL ERROR: Not enough positional arguments - we require exactly 2 (namely: $_required_args_string), but got only ${_positionals_count}." 1
	test "${_positionals_count}" -le 2 || _PRINT_HELP=yes die "FATAL ERROR: There were spurious positional arguments --- we expect exactly 2 (namely: $_required_args_string), but got ${_positionals_count} (the last one was: '${_last_positional}')." 1
}


assign_positional_args()
{
	local _positional_name _shift_for=$1
	_positional_names="_arg_testcase_home _arg_target_dir "

	shift "$_shift_for"
	for _positional_name in ${_positional_names}
	do
		test $# -gt 0 || break
		eval "$_positional_name=\${1}" || die "Error during argument parsing, possibly an Argbash bug." 1
		shift
	done
}

parse_commandline "$@"
handle_passed_args_count
assign_positional_args 1 "${_positionals[@]}"

# OTHER STUFF GENERATED BY Argbash
BUILD_HOME="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)" || die "Couldn't determine the script's running directory, which probably matters, bailing out" 2

### END OF CODE GENERATED BY Argbash (sortof) ### ])
# [ <-- needed because of Argbash
#
getCommitter(){
	BRANCH_URL=$1
	proto="$(echo $BRANCH_URL | grep :// | sed -e's,^\(.*://\).*,\1,g')"
	url="$(echo ${BRANCH_URL/$proto/})"
	echo $url | grep / | cut -d '/' -f 2
}

TEST_TIME=`date "+%Y-%m-%d-%H-%M"`
TEST_TIME_YEAR=`echo $TEST_TIME | cut -d '-' -f 1`
TEST_TIME_MONTH_STR=`echo $TEST_TIME | cut -d '-' -f 2`
TEST_TIME_MONTH=$((10#$TEST_TIME_MONTH_STR))
TEST_TIME_MONTH=${TEST_TIME_MONTH#0}
TEST_CASES_BRANCH=${_arg_testcase_branch}
AGENT_BRANCH=${_arg_agent_branch}
TEST_CASES_DIR=`cd ${_arg_testcase_home} >/dev/null; pwd`
REPORT_DIR=`cd ${_arg_target_dir} >/dev/null; pwd`
VALIDATE_TOOL_JAR_HOME=`cd ${BUILD_HOME}/${_arg_validate_tool_jar_home} >/dev/null; pwd`
NORMALIZED_TEST_CASES_BRANCH=${TEST_CASES_BRANCH//\//-}
COMMITTER=`getCommitter ${_arg_agent_repo}`
TEST_CASES_COMMITID=${_arg_testcase_commitid}
AGENT_COMMIT=${_arg_agent_commitid}
ISSUE_NO=${_arg_issue_no}
VALIDATE_LOG_URL_PREFIX=${_arg_validate_log_url_prefix}

java -DtestDate="$TEST_TIME" \
	-DagentBranch="$AGENT_BRANCH" -DagentCommit="$AGENT_COMMIT" \
	-DtestCasePath="$TEST_CASES_DIR" -DreportFilePath="$REPORT_DIR" \
	-DcasesBranch="$NORMALIZED_TEST_CASES_BRANCH" -DcasesCommitId="${TEST_CASES_COMMITID}" \
	-Dcommitter="$COMMITTER"	\
	-jar ${VALIDATE_TOOL_JAR_HOME}/skywalking-autotest.jar > ${_arg_log_dir}/validate_${TEST_TIME}.log

if [ ! -f "$REPORT_DIR/${AGENT_GIT_BRANCH}" ]; then
	mkdir -p $REPORT_DIR/${AGENT_GIT_BRANCH}
fi

if [ "${_arg_overwrite_readme}" = "on" ]; then
	cp -f $REPORT_DIR/${TEST_TIME_YEAR}/${TEST_TIME_MONTH}/${COMMITTER}/testReport-${NORMALIZED_TEST_CASES_BRANCH}-${TEST_TIME}.md $REPORT_DIR/README.md
fi

if [ "${_arg_upload_report}" = "on" ]; then
    cd ${_arg_target_dir} && git add $REPORT_DIR/README.md && git add $REPORT_DIR/${TEST_TIME_YEAR}/${TEST_TIME_MONTH}/${COMMITTER}/testReport-${NORMALIZED_TEST_CASES_BRANCH}-${TEST_TIME}.md && git commit -m "push report report-${TEST_TIME}.md" .

    if [ ! -z "$GITHUB_ACCOUNT" ]; then
	    git config remote.origin.url https://${GITHUB_ACCOUNT}@github.com/SkywalkingTest/agent-integration-test-report.git
    fi

    git push origin master

    if [ "$ISSUE_NO" = "UNKNOWN" ]; then
        echo "issue no is empty, ignore to push comment"
    else
      curl --user ${GITHUB_ACCOUNT} -X POST -H "Content-Type: text/plain" -d "{\"body\":\"Here is the [test report](http://github.com/SkywalkingTest/agent-integration-test-report/tree/master/${TEST_TIME_YEAR}/${TEST_TIME_MONTH}/${COMMITTER}/testReport-${NORMALIZED_TEST_CASES_BRANCH}-${TEST_TIME}.md) and [validate logs](${VALIDATE_LOG_URL_PREFIX}/validate_${TEST_TIME}.log)\"}" https://api.github.com/repos/apache/incubator-skywalking/issues/${ISSUE_NO}/comments

      #echo "[INFO] Test report: http://github.com/SkywalkingTest/agent-integration-test-report/tree/master/${TEST_TIME_YEAR}/${TEST_TIME_MONTH}/${COMMITTER}/testReport-${NORMALIZED_TEST_CASES_BRANCH}-${TEST_TIME}.md"
      #echo "[INFO] Validate logs: ${VALIDATE_LOG_URL_PREFIX}/validate-$TEST_TIME.log"
    fi
fi

echo ""
echo ""
echo "[INFO] ---------------------------Here is the test report context -------------------------------------"
echo ""
cat $REPORT_DIR/${TEST_TIME_YEAR}/${TEST_TIME_MONTH}/${COMMITTER}/testReport-${NORMALIZED_TEST_CASES_BRANCH}-${TEST_TIME}.md
echo ""
echo "[INFO] -----------------------------------end the report-----------------------------------------------"
#
# ] <-- needed because of Argbash
