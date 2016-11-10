package alien4cloud.packager;

import alien4cloud.git.RepositoryManager;
import alien4cloud.utils.FileUtil;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility that downloads the archives we want to package.
 */
public class ArchiveDownloader {

    public static void main(String[] args) throws IOException {
        Path buildDirectory = Paths.get(args[0]);
        Path gitDirectory = buildDirectory.resolve("git");
        Path zipDirectory = buildDirectory.resolve("archives");

        RepositoryManager.cloneOrCheckout(gitDirectory, "https://github.com/alien4cloud/tosca-normative-types.git", "1.2.0", "tosca-normative-types");
        RepositoryManager.cloneOrCheckout(gitDirectory, "https://github.com/alien4cloud/alien4cloud-extended-types.git", "1.2.0",
                "alien4cloud-extended-types");
        // Do we want to initialize alien with sample topology.
        // repositoryManager.cloneOrCheckout(gitDirectory, "https://github.com/alien4cloud/samples.git", "master", "samples");

        FileUtil.zip(gitDirectory.resolve("tosca-normative-types"), zipDirectory.resolve("tosca-normative-types.zip"));
        FileUtil.zip(gitDirectory.resolve("alien4cloud-extended-types/alien-extended-storage-types"), zipDirectory.resolve("alien-extended-storage-types.zip"));
        // zip archives
        // FileUtil.zip(from, to);
    }
}
