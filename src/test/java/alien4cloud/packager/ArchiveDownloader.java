package alien4cloud.packager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import alien4cloud.git.RepositoryManager;
import alien4cloud.utils.FileUtil;

/**
 * Utility that downloads the archives we want to package.
 */
public class ArchiveDownloader {
    private final static RepositoryManager repositoryManager = new RepositoryManager();

    public static void main(String[] args) throws IOException {
        Path buildDirectory = Paths.get(args[0]);
        Path gitDirectory = buildDirectory.resolve("git");
        Path zipDirectory = buildDirectory.resolve("archives");

        repositoryManager.cloneOrCheckout(gitDirectory, "https://github.com/alien4cloud/tosca-normative-types.git", "master", "tosca-normative-types");
        repositoryManager.cloneOrCheckout(gitDirectory, "https://github.com/alien4cloud/alien4cloud-extended-types.git", "master",
                "alien4cloud-extended-types");
        // Do we want to initialize alien with sample topology.
        // repositoryManager.cloneOrCheckout(gitDirectory, "https://github.com/alien4cloud/samples.git", "master", "samples");

        FileUtil.zip(gitDirectory.resolve("tosca-normative-types"), zipDirectory.resolve("tosca-normative-types.zip"));
        FileUtil.zip(gitDirectory.resolve("alien4cloud-extended-types/alien-extended-storage-types-1.0-SNAPSHOT"),
                zipDirectory.resolve("alien-extended-storage-types.zip"));
        // zip archives
        // FileUtil.zip(from, to);
    }
}