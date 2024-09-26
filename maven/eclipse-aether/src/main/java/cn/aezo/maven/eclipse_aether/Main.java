package cn.aezo.maven.eclipse_aether;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResult;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.file.FileTransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator;

import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws Exception {
        downloadJar();

        resolveDependency();
    }

    private static void downloadJar() throws Exception {
        // 创建 RepositorySystem
        DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
        locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
        locator.addService(TransporterFactory.class, FileTransporterFactory.class);
        locator.addService(TransporterFactory.class, HttpTransporterFactory.class);
        RepositorySystem system = locator.getService(RepositorySystem.class);

        // 创建 RepositorySystemSession
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
        // 设置本地仓库，用于保存从远程仓库下载的依赖
        LocalRepository localRepo = new LocalRepository("/Users/smalle/.m2/repository");
        session.setLocalRepositoryManager(system.newLocalRepositoryManager(session, localRepo));

        // 设置 Artifact 坐标
        Artifact artifact = new DefaultArtifact("org.apache.maven", "maven-aether-provider", "jar", "3.1.0");
        ArtifactRequest artifactRequest = new ArtifactRequest();
        artifactRequest.setArtifact(artifact);

        // 设置查找的远程仓库，可设置多个
        //Authentication authentication=new AuthenticationBuilder().addUsername(username).addPassword(password).build();
        //central = new RemoteRepository.Builder( "central", "default", repositoryUrl ).setAuthentication(authentication).build();
        artifactRequest.addRepository(new RemoteRepository.Builder("central", "default", "http://maven.aliyun.com/nexus/content/groups/public/").build());

        // 解析 Artifact
        ArtifactResult artifactResult = system.resolveArtifact(session, artifactRequest);
        artifact = artifactResult.getArtifact();
        // 根据 Artifact jar 路径创建 ClassLoader
        // file:/Users/smalle/.m2/repository/org/apache/maven/maven-aether-provider/3.1.0/maven-aether-provider-3.1.0.jar
        URL artifactUrl = artifact.getFile().toURI().toURL();
        ClassLoader classLoader = new URLClassLoader(new URL[] { artifactUrl });

        System.out.println("classLoader = " + classLoader);
        System.out.println("artifact.getFile().toURI().toURL() = " + artifact.getFile().toURI().toURL());
    }

    private static void resolveDependency() throws Exception {
        RepositorySystem repoSystem = newRepositorySystem();
        RepositorySystemSession session = newSession(repoSystem, "target");
        Dependency dependency = new Dependency( new DefaultArtifact( "org.apache.maven:maven-aether-provider:3.1.0" ), "compile" );
        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setRoot( dependency );
        collectRequest.addRepository(new RemoteRepository.Builder("central", "default", "http://maven.aliyun.com/nexus/content/groups/public/").build());
        DependencyNode node = repoSystem.collectDependencies( session, collectRequest ).getRoot();
        DependencyRequest dependencyRequest = new DependencyRequest();
        dependencyRequest.setRoot( node );
        repoSystem.resolveDependencies( session, dependencyRequest  );
        PreorderNodeListGenerator nlg = new PreorderNodeListGenerator();
        node.accept( nlg );
        System.out.println(" ================================== ");
        System.out.println( nlg.getClassPath() );
    }

    private static RepositorySystem newRepositorySystem() {
        DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
        locator.addService( RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class );
        locator.addService( TransporterFactory.class, FileTransporterFactory.class );
        locator.addService( TransporterFactory.class, HttpTransporterFactory.class );

        return locator.getService( RepositorySystem.class );
    }

    private static RepositorySystemSession newSession( RepositorySystem system,String target ) {
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();

        //LocalRepository localRepo = new LocalRepository( /*"target/local-repo"*/target);
        LocalRepository localRepo = new LocalRepository("/Users/smalle/.m2/repository");
        session.setLocalRepositoryManager( system.newLocalRepositoryManager( session, localRepo ) );

        return session;
    }
}
